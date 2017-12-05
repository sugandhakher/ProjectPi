package program;

import java.util.List;

/**
 * Interface Node
 * Role: Component Interface
 * Node is the basic unit of AST
 */
public interface Node {

    /**
     * Returns list of all nodes in tree
     */
    List<Node> getChildren();

    /**
     * Add one node to be the child of the node
     */
    void addChild(Node n);

    /**
     * Return label of the node
     */
    String getLabel();

    /**
     * Sets the label of the node as given in argument
     */
    void setLabel(String l);

    /**
     * Returns Context
     */
    String getContext();

    /**
     * Set the context of node
     */
    void setContext(String c);

    /**
     * Return the start Line of program
     */
    int getStartline();

    /**
     * Return last line of the program
     */
    int getEndline();

    /**
     * Accepts the visitor object of JoinCallVisitor
     */
    void accept(Visitor v);
}
