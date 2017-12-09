package program;

import loader.NodeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implement Python program to traverse through nodes
 */
public class PythonNode implements Node {

    /**
     * Variables:
     */
    NodeAdapter adaptee;
    String label; // node type
    String context;
    int index; // preorder index
    // note: trees need not be binary
    ArrayList<Node> children = new ArrayList<Node>();
    PythonNode leftmost; // used by the recursive O(n) leftmost() function

    /**
     * Constructor that takes NodeAdaptor and assigns its context, label, start line and end line to Python node
     */
    public PythonNode(NodeAdapter adp) {
        adaptee = adp;
        label = adp.getNodeLabel();
        context = adp.getNodeContext();


    }

    /**
     * Constructor that assigns the given String to label of Python node
     */
    public PythonNode(String l) {
        this.label = l;
    }

    /**
     * Returns list of all nodes in tree
     */
    @Override
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Add one node to be the child of the node
     */
    @Override
    public void addChild(Node n) {
        children.add(n);
    }

    /**
     * Return label of the node
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label of the node as given in argument
     */
    @Override
    public void setLabel(String l) {
        this.label = l;
    }

    /**
     * Returns Context
     */
    @Override
    public String getContext() {
        return context;
    }

    /**
     * Set the context of node
     */
    @Override
    public void setContext(String c) {
        this.context = c;
    }

    /**
     * Return the start Line of program
     */
    @Override
    public int getStartline() {
        if(adaptee != null)
            return adaptee.getStartLine();
        else
            return 0;
    }

    /**
     * Return last line of the program
     */
    @Override
    public int getEndline() {

        if (adaptee != null)
            return adaptee.getEndLine();
        else
            return 0;
    }

    /**
     * Accepts the visitor object of JoinCallVisitor
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for (Node n : this.getChildren()) {
            n.accept(v);
        }
    }

    /**
     * Return index of the Node
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets index of node as given in argument
     */
    public void setIndex(int i) {
        index = i;
    }

    /**
     * Returns leftmose node in PythonNode
     */
    public PythonNode getLeftmost() {
        return leftmost;
    }
}