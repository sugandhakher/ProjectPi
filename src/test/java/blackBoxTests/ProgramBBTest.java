package blackBoxTests;

import loader.Loader;
import loader.LoaderFactory;
import program.Program;
import program.PythonProgramFactory;
import program.PythonTree;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class ProgramBBTest {

    LoaderFactory lfactory = new LoaderFactory();

    // test multiple root nodes returned for multiple definition program
    @Test
    public void test() throws IOException {
        Loader loader = lfactory.makePythonLoader("examples/medium/4A");
        Program p = loader.parse();
        PythonTree ptree1 = (PythonTree) p.getAllTrees().get(0);
        PythonTree ptree2 = (PythonTree) p.getAllTrees().get(1);

        assertEquals(ptree1.getRoot().getLabel().equals("main"), true);

        assertEquals(ptree2.getRoot().getLabel().equals("funcdef"), true);
    }
}