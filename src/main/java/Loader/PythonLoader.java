package loader;

import program.Program;
import program.PythonProgramFactory;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import pythonast.parser.Python3Parser;

import java.io.File;
import java.io.IOException;

import program.*;

/**
 * Class PythonLoader
 * Role: one implementation of loader for python language
 */
public class PythonLoader implements Loader {
    /**
     * URL of project
     */
    private String URL;
    private PythonProgramFactory pfactory = new PythonProgramFactory();
    private boolean ignoringWrappers = true;

    public PythonLoader(String url) {
        URL = url;
    }

    /**
     * Return the url of this load
     */
    @Override
    public String getURL() {
        return URL;
    }


    /**
     * Build a program data structure defined in package program based on python program
     */
    public Program parse() throws IOException {
        ParserFacade parserFacade = new ParserFacade();
        Program program = pfactory.makePythonProgram(URL);

        File directory = new File(URL);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                String path = URL + "/" + file.getName();

                RuleContext ctx = parserFacade.parse(new File(path));
                build(ctx, program, file.getName());
            }
        }
        program = jointCalls(program);
        return program;
    }

    /**
     * second type to build AST, in this way, each project will become one huge AST
     */
    @Override
    public Program parse2() throws IOException {
        ParserFacade parserFacade = new ParserFacade();
        Program program = pfactory.makePythonProgram(URL);

        File directory = new File(URL);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                String path = URL + "/" + file.getName();

                RuleContext ctx = parserFacade.parse(new File(path));
                build(ctx, program, file.getName());
            }
        }
        PythonNode node = pfactory.makePythonNode("program");
        Program program2 = pfactory.makePythonProgram(URL);
        SearchTree newTree = pfactory.makePythonTree(node, URL);
        for (Tree t : program.getAllTrees()) {
            newTree.getRoot().addChild(t.getRoot());
        }

        program2.getAllTrees().add(newTree);

        return program2;
    }

//    Used in printAST
//    private void setIgnoringWrappers(boolean ignoringWrappers) {
//        this.ignoringWrappers = ignoringWrappers;
//    }

    private Program build(RuleContext ctx, Program program, String fileName) {
        buildProgram(ctx, null, program, fileName);
        return program;

    }

    private Program jointCalls(Program program) {
        Visitor visitor = new JoinCallVisitor(program);
        for (SearchTree tree : program.getAllTrees()) {
            tree.getRoot().accept(visitor);
        }
        return program;
    }


    private void buildProgram(RuleContext ctx, Node currentroot, Program program, String fileName) {
        //       printAST(ctx,0);
        //set ignored node
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount() == 1
                && ctx.getChild(0) instanceof ParserRuleContext;
        String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
        if (!toBeIgnored && ruleName != "file_input") {

            if (ruleName == "funcdef") {
                NodeAdapter adapter = new ANTLRNodeAdapter((ParserRuleContext) ctx);
                PythonNode node = pfactory.makePythonNode(adapter);
                String fileURL = URL + "/" + fileName;
                PythonTree tree = pfactory.makePythonTree(node, fileName);

                program.addTree(tree);
                currentroot = node;
            } else if (currentroot != null) {
                NodeAdapter adapter = new ANTLRNodeAdapter((ParserRuleContext) ctx);
                PythonNode newNode = pfactory.makePythonNode(adapter);
                currentroot.addChild(newNode);
                currentroot = newNode;
            } else {
                if (!program.hasMain()) {
                    PythonNode node = pfactory.makePythonNode("main");
                    String fileURL = URL + "/" + fileName;

                    PythonTree tree = pfactory.makePythonTree(node, fileName);
                    program.addTree(tree);
                    currentroot = node;
                    program.setMain();
                    NodeAdapter adapter = new ANTLRNodeAdapter((ParserRuleContext) ctx);
                    PythonNode newNode = pfactory.makePythonNode(adapter);
                    currentroot.addChild(newNode);
                    currentroot = newNode;
                } else {
                    for (SearchTree tree : program.getAllTrees()) {
                        if (tree.getRoot().getLabel().equals("main")) {
                            currentroot = tree.getRoot();
                            NodeAdapter adapter = new ANTLRNodeAdapter((ParserRuleContext) ctx);
                            PythonNode newNode = pfactory.makePythonNode(adapter);
                            currentroot.addChild(newNode);
                            currentroot = newNode;
                        }
                    }
                }


            }

        }
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                buildProgram((RuleContext) element, currentroot, program, fileName);

            }
        }

    }


//    private void printAST(RuleContext ctx, int indentation) {
//        boolean toBeIgnored = ignoringWrappers
//                && ctx.getChildCount() == 1
//                && ctx.getChild(0) instanceof ParserRuleContext;
//
//        if (!toBeIgnored) {
//            String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
//            for (int i = 0; i < indentation; i++) {
//                System.out.print("  ");
//            }
//
//            System.out.println(ruleName);
//        }
//        for (int i=0;i<ctx.getChildCount();i++) {
//            ParseTree element = ctx.getChild(i);
//            if (element instanceof RuleContext) {
//                printAST((RuleContext)element, indentation + (toBeIgnored ? 0 : 1));
//            }
//        }
//
//    }

}
