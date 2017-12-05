package program;

import java.util.ArrayList;

/**
 * Extended interface of Tree that implements the Tree to be Searched for comparison
 */
public interface SearchTree extends Tree {

    /**
     * Return list of keyroots in search tree
     */
    ArrayList<Integer> getKeyroots();

    /**
     * Returns list of labels in search tree
     */
    ArrayList<String> getLabels();

    /**
     * Traverse the tree for nodes
     */
    void traverse();

    /**
     * Left subtree of the tree
     */
    void l();

    /**
     * Searches the keyroots in the tree
     */
    void keyroots();

    /**
     * Searches for index in tree
     */
    void index();

    /**
     * Returns list of leftmost node of the tree
     */
    ArrayList<Integer> getLeftmost();

    /**
     * Return URL provided for searching the tree
     */
    String getURL();
}
