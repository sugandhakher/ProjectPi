package Program;

import java.util.List;
/** Interface Node
 * Role: Component Interface
 * Node is the basic unit of AST
 */

public interface Node {

    /** getChildren
     * get all childrens in list
     */
    List<Node> getChildren();
    /** addChild
     * add one node to be the child of this node
     * @param n: the node to be child
     */
    void addChild(Node n);
    /** getLabel/ set Label
     * get and set the label of this node
     */
    String getLabel();
    void setLabel(String l);

    String getContext();
    void setContext(String c);

    int getStartline();
    int getEndline();
    void accept(Visitor v);
}
