package loader;

/**
 * Interface to create nodes for python program
 */
public interface NodeAdapter {

    /**
     * Return label of Python node
     */
    String getNodeLabel();

    /**
     * Return context of python node
     */
    String getNodeContext();

    /**
     * Return start line of Python program
     */
    int getStartLine();

    /**
     * Return end line of python program
     */
    int getEndLine();
}
