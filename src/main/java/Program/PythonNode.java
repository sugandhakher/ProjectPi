package Program;

import Loader.NodeAdapter;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class PythonNode implements Node {
    NodeAdapter adaptee;
    String label; // node type
    String context;
    int index; // preorder index
    // note: trees need not be binary
    ArrayList<Node> children = new ArrayList<Node>();
    PythonNode leftmost; // used by the recursive O(n) leftmost() function
    int startline;
    int endline;

    public PythonNode(NodeAdapter adp) {

        label = adp.getNodeLabel();
        context = adp.getNodeContext();
        startline = adp.getStartLine();
        endline = adp.getEndLine();


    }

    public PythonNode(String l) {
        this.label = l;
        startline = 0;
        endline = 0;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node n){
        children.add(n);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String l) {
        this.label = l;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public void setContext(String c) {
        this.context = c;
    }

    @Override
    public int getStartline() {
        return startline;
    }

    @Override
    public int getEndline() {
        return endline;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for (Node n :this.getChildren()){
            n.accept(v);
        }
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int i){
        index = i;
    }

    public PythonNode getLeftmost(){
       return leftmost;
    }
}