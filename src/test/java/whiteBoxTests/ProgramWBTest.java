package whiteBoxTests;

import loader.Loader;
import loader.LoaderFactory;
import org.junit.Test;
import program.Node;
import program.Program;
import program.PythonProgramFactory;
import program.PythonTree;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class ProgramWBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();

    @Test
    public void test() throws IOException{
        Program p = pfactory.makePythonProgram("examples/medium/4A");
        assertEquals(p.getURL(), "examples/medium/4A");
    }

    @Test
    public void test2() throws IOException{
        Node n = pfactory.makePythonNode("examples/medium/4A");
        n.setLabel("testlabel");
        n.setContext("defA");
        assertEquals(n.getLabel(), "testlabel");
        assertEquals(n.getContext(), "defA");
    }
}