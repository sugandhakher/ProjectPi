package blackBoxTests;

import loader.Loader;
import program.Program;
import program.PythonProgramFactory;
import loader.LoaderFactory;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoaderBBTest {

    LoaderFactory lfactory = new LoaderFactory();

    @Test
    public void test1() throws IOException {
        Loader loader = lfactory.makePythonLoader("examples/medium/4A");
        Program p = loader.parse();

        assertEquals(p.getAllTrees().size(), 2);
    }

    @Test
    public void test2() throws IOException {
        Loader loader = lfactory.makePythonLoader("examples/medium/1A");
        Program p = loader.parse();

        assertNotEquals(p.getAllTrees().size(), 1);
    }
}
