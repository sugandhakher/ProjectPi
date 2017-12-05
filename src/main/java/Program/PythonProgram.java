package program;

import java.util.ArrayList;
import java.util.List;

public class PythonProgram implements Program {
    /**
     * Variables:
     */
    private List<SearchTree> trees;
    private String URL;
    public boolean main = false;

    /**
     * Return URL when provided
     */
    @Override
    public String getURL() {
        return URL;
    }

    /**
     * Default Constructor
     */
//    public PythonProgram() {
//        trees = new ArrayList<SearchTree>();
//    }

    /**
     * Constructor that takes given URL and assign it to url attribute
     */
    public PythonProgram(String url) {
        trees = new ArrayList<SearchTree>();
        URL = url;
    }

    /**
     * Return list of all nodes in the program
     */
    @Override
    public List<SearchTree> getAllTrees() {
        return trees;
    }

    /**
     * Add given tree in list of tree
     */
    @Override
    public void addTree(SearchTree t) {
        trees.add(t);
    }

    /**
     * Prints the label of the node
     */
//    @Override
//    public void printLabel(){
//        int indent = 0;
//        for(SearchTree tree : trees){
//            System.out.println(tree.getRoot().getLabel() + "  :  " + tree.getRoot().getContext());
//            for(Node n :tree.getRoot().getChildren()){
//                print(n,indent+1);
//            }
//        }
//    }

    /**
     * return true if main is present
     */
    @Override
    public boolean hasMain() {
        return main;
    }

    /**
     * Set main as true
     */
    @Override
    public void setMain() {
        main = true;
    }


    /**
     * Helper function for printLabel
     */
//    private void print(Node n, int indent){
//        for(int i =0 ; i<indent; i++){
//        System.out.print("   ");
//        }
//        System.out.print(n.getLabel());
//        System.out.print("  :  ");
//        System.out.println(n.getContext());
//        for(Node node :n.getChildren()){
//            print(node,indent+1);
//        }
//    }

}
