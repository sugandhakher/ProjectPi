package program;

import loader.NodeAdapter;

/**
 * Factory class that creates objects for PythonNode, PythnTree and PythonProgram
 */
public class PythonProgramFactory {

    /**
     * Creates object of PythonNode when NodeAdapter is given
     */
    public PythonNode makePythonNode(NodeAdapter adp) {
        return new PythonNode(adp);
    }

    /**
     * Creates object of PythonNode when label is given
     */
    public PythonNode makePythonNode(String s) {
        return new PythonNode(s);
    }

    //public PythonTree makePythonTree(String s) throws IOException{return new PythonTree(s);}

    /**
     * Creates object of PythonTree when node and the url is given
     */
    public PythonTree makePythonTree(PythonNode node, String url) {
        return new PythonTree(node, url);
    }

    /**
     * Creates default object of PythonProgram
     */
//    public PythonProgram makePythonProgram(){return new PythonProgram();}

    /**
     * Creates object of PythonProgram when url is given
     */
    public PythonProgram makePythonProgram(String url) {
        return new PythonProgram(url);
    }
}
