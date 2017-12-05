package program;

import java.util.ArrayList;

public class PythonTree implements SearchTree {

    /**
     * Variables:
     */
    PythonNode root;
    String URL;
    // function l() which gives the leftmost child
    ArrayList<Integer> l = new ArrayList<Integer>();
    // list of keyroots, i.e., nodes with a left child and the tree root
    ArrayList<Integer> keyroots = new ArrayList<Integer>();
    // list of the labels of the nodes used for node comparison
    ArrayList<String> labels = new ArrayList<String>();


    /**
     * Constructor that takes PythonNode as root and url
     */
    public PythonTree(PythonNode r, String url) {
        root = r;
        URL = url;
    }

    /**
     * Returns list of leftmost node of the tree
     */
    @Override
    public ArrayList<Integer> getLeftmost() {
        return l;
    }

    /**
     * Return URL provided for searching the tree
     */
    @Override
    public String getURL() {
        return URL;
    }

    /**
     * Return list of keyroots in search tree
     */
    @Override
    public ArrayList<Integer> getKeyroots() {
        return keyroots;
    }

    /**
     * Returns list of labels in search tree
     */
    @Override
    public ArrayList<String> getLabels() {
        return labels;
    }

    /**
     * Return root of the Python Tree
     */
    @Override
    public Node getRoot() {
        return root;
    }

    /**
     * Return size of the tree
     */
    @Override
    public int size() {
        int size = 1;
        size = getSize(root, size);
        return size;
    }

    /**
     * Helper function of size() that returns size of tree given the node
     */
    private int getSize(Node node, int size) {
        size += node.getChildren().size();
        for (Node n : node.getChildren()) {
            size = getSize(n, size);
        }
        return size;

    }

    /**
     * Traverse the tree for nodes
     */
    @Override
    public void traverse() {
        // put together an ordered list of node labels of the tree
        traverse(root, labels);
    }

    /**
     * Helper function of traverse()
     */
    private static ArrayList<String> traverse(Node node, ArrayList<String> labels) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            labels = traverse(node.getChildren().get(i), labels);
        }
        labels.add(node.getLabel());
        return labels;
    }

    /**
     * Searches for index in tree
     */
    @Override
    public void index() {
        // index each node in the tree according to traversal method
        index(root, 0);
    }

    /**
     * Helper function of index()
     */
    private static int index(Node node, int index) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            index = index(node.getChildren().get(i), index);
        }
        index++;
        ((PythonNode) node).setIndex(index);
        return index;
    }

    /**
     * Left subtree of the tree
     */
    @Override
    public void l() {
        // put together a function which gives l()
        leftmost();
        l = l((PythonNode) root, new ArrayList<Integer>());
    }

    /**
     * Helper function of l()
     */
    private ArrayList<Integer> l(PythonNode node, ArrayList<Integer> l) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            l = l((PythonNode) node.getChildren().get(i), l);
        }
        l.add(node.getLeftmost().getIndex());
        return l;
    }

    /**
     * Returns the leftmost root of the tree
     */
    private void leftmost() {
        leftmost(root);
    }

    /**
     * Helper function to search for leftmost root
     */
    private static void leftmost(PythonNode node) {
        //controled in loader
//        if (node == null)
//            return;
        for (int i = 0; i < node.getChildren().size(); i++) {
            leftmost((PythonNode) node.getChildren().get(i));
        }
        if (node.getChildren().size() == 0) {
            node.leftmost = node;
        } else {
            PythonNode tempnode = (PythonNode) node.children.get(0);
            node.leftmost = tempnode.getLeftmost();
        }
    }

    //LR_keyroots(T)= {klthere exists no k’> k such that/(k)= l(k’)}.

    /**
     * Searches the keyroots in the tree
     */
    @Override
    public void keyroots() {
        // calculate the keyroots
        for (int i = 0; i < l.size(); i++) {
            int flag = 0;
            for (int j = i + 1; j < l.size(); j++) {
                if (l.get(j) == l.get(i)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                this.keyroots.add(i + 1);
            }
        }
    }
}