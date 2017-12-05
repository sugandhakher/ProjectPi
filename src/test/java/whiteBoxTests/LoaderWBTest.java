package whiteBoxTests;

import loader.Loader;
import loader.LoaderFactory;
import org.junit.Test;
import program.Program;
import program.PythonProgramFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoaderWBTest {

    LoaderFactory lfactory = new LoaderFactory();

    // White box - test coverage
    @Test
    public void test() throws IOException{
        Loader loader = lfactory.makePythonLoader("examples/medium/1A");
        assertEquals(loader.getURL(), "examples/medium/1A");
    }
}
