package Loader;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import pythonast.parser.Python3Lexer;
import pythonast.parser.Python3Parser;

public class ANTLRNodeAdapter implements NodeAdapter {
    private String nodeLabel;
    private String nodeContent;
    private int startline;
    private int endline;
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

    @Override
    public String getNodeLabel() {
        return nodeLabel;
    }

    @Override
    public String getNodeContext() {
        return nodeContent;
    }

    @Override
    public int getStartLine() {
        return startline;
    }

    @Override
    public int getEndLine() {
        return endline;
    }
}
