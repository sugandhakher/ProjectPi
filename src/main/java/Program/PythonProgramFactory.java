package Program;

import Loader.NodeAdapter;

import java.io.IOException;

public class PythonProgramFactory {
    public PythonNode makePythonNode(NodeAdapter adp){return new PythonNode(adp);}
    public PythonNode makePythonNode(String s){return new PythonNode(s);}

    //public PythonTree makePythonTree(String s) throws IOException{return new PythonTree(s);}
    public PythonTree makePythonTree(PythonNode node, String url){return new PythonTree(node, url);}

    public PythonProgram makePythonProgram(){return new PythonProgram();}
    public PythonProgram makePythonProgram(String url){return new PythonProgram(url);}
}
