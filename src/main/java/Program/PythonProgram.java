package Program;

import java.util.ArrayList;
import java.util.List;

public class PythonProgram implements Program{
    private List<SearchTree> trees;
    private String URL;
    public boolean main = false;

    @Override
    public String getURL() {
        return URL;
    }

    public PythonProgram(){
        trees = new ArrayList<SearchTree>();
    }
    public PythonProgram(String url){
        trees = new ArrayList<SearchTree>();
        URL = url;
    }
    @Override
    public List<SearchTree> getAllTrees() {
        return trees;
    }

    @Override
    public void addTree(SearchTree t) {
        trees.add(t);
    }

    @Override
    public void printLabel(){
        int indent = 0;
        for(SearchTree tree : trees){
            System.out.println(tree.getRoot().getLabel() + "  :  " + tree.getRoot().getContext());
            for(Node n :tree.getRoot().getChildren()){
                print(n,indent+1);
            }
        }
    }

    @Override
    public boolean hasMain() {
        return main;
    }

    @Override
    public void setMain() {
        main = true;
    }

    private void print(Node n, int indent){
        for(int i =0 ; i<indent; i++){
        System.out.print("   ");
        }
        System.out.print(n.getLabel());
        System.out.print("  :  ");
        System.out.println(n.getContext());
        for(Node node :n.getChildren()){
            print(node,indent+1);
        }
    }

}
