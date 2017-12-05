package program;


import java.util.List;

/**
 * Interface Program
 * Role: component interface
 * This component is our representation of a python program, we will use this data structure to run analysis.
 * The advantage of this component is , we bond node to the position of itsself. so it will be easier for visualization
 */
public interface Program {

    /**
     * Return list of all nodes in the program
     */
    List<SearchTree> getAllTrees();

    /**
     * Add given tree in list of tree
     */
    void addTree(SearchTree t);

    /**
     * Return URL when provided
     */
    String getURL();

    /** return a map, the key is all sub-route of input path, and value is a set of node in this sub-route
     * @param path: the route path of a file or dictionary in this program
     */
    //public Map<String, Set<Node>> getProgramMap(String path);

    /** return a map, the key is the path of a file, and the integer set is the lines corrospond to input node
     * @param node: the node to be searched
     */
    //public Map<String, Set<Integer>> getNodeCoveredLine(Node node);

    /**
     * Prints the label of the node
     */
//    void printLabel();

    /**
     * return true if main is present
     */
    boolean hasMain();

    /**
     * Set main as true
     */
    void setMain();

}
