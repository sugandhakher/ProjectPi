package loader;

import org.antlr.v4.runtime.ParserRuleContext;
import pythonast.parser.Python3Parser;

public class ANTLRNodeAdapter implements NodeAdapter {

    /**
     * Variables:
     */
    private String nodeLabel;
    private String nodeContent;
    private int startline;
    private int endline;


    /**
     * Constructor
     */
    public ANTLRNodeAdapter(ParserRuleContext ctx){
        nodeLabel = Python3Parser.ruleNames[ctx.getRuleIndex()];
        startline = ctx.getStart().getLine();
        endline = ctx.getStop().getLine();
        nodeContent = ctx.getText();
        if("funcdef" == nodeLabel){
            int i = 0;
            String funcName = "";
            for (char c : nodeContent.toCharArray()){
                if(i>2){
                    if(c != "(".toCharArray()[0])
                    {
                        funcName += c;
                    }
                    else{
                        break;
                    }
                }
                i++;
            }
            nodeContent = funcName;
        }
    }

    /**
     * Return label of Python node
     */
    @Override
    public String getNodeLabel() {
        return nodeLabel;
    }

    /**
     * Return context of python node
     */
    @Override
    public String getNodeContext() {
        return nodeContent;
    }

    /**
     * Return start line of Python program
     */
    @Override
    public int getStartLine() {
        return startline;
    }

    /**
     * Return end line of python program
     */
    @Override
    public int getEndLine() {
        return endline;
    }
}
