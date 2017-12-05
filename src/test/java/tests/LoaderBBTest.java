package tests;

import loader.Loader;
import program.Program;
import program.PythonProgramFactory;
import loader.LoaderFactory;
import loader.Loader;
import program.Program;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class LoaderBBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();
    LoaderFactory lfactory = new LoaderFactory();

    @Test
    public void test1() throws IOException{
        Loader loader = lfactory.makePythonLoader("examples/medium/4A");
        Program p = loader.Parse();

        assertEquals(p.getAllTrees().size(), 2);
    }

    @Test
    public void test2() throws IOException{
        Loader loader = lfactory.makePythonLoader("examples/medium/1A");
        Program p = loader.Parse();

        assertNotEquals(p.getAllTrees().size(), 1);
    }

    // White box - test coverage
    @Test
    public void test3() throws IOException{
        Loader loader = lfactory.makePythonLoader("examples/medium/1A");
        assertEquals(loader.getURL(), "examples/medium/1A");
    }
//    @Test
//    public void test1() throws IOException{
//        PythonLoader loader = lfactory.makePythonLoader();
//        program p = loader.Parse("examples/simple/simple.py");
//        assertEquals(p.getAllTrees().get(0).getRoot().getLabel(), "funcdef");
//        assertEquals(p.getAllTrees().get(0).getRoot().getChildren().get(0).getLabel(), "parameters");
//        assertEquals(p.getAllTrees().get(0).getRoot().getChildren().get(1).getLabel(), "suite");
//    }
//
//
//    //three function program
//    @Test
//    public void test2() throws IOException{
//        PythonLoader loader = lfactory.makePythonLoader();
//        program p = loader.Parse("examples/medium/medium_A_Split.py");
//        assertEquals(p.getAllTrees().get(0).getRoot().getLabel(), "funcdef");
//        assertEquals(p.getAllTrees().get(1).getRoot().getLabel(), "funcdef");
//        assertEquals(p.getAllTrees().get(2).getRoot().getLabel(), "funcdef");
//
//    }
//
//    @Test
//    public void test3() throws IOException{
//        PythonLoader loader = lfactory.makePythonLoader();
//        program p = loader.Parse("examples/simple/simpleSplitB.py");
////        assertEquals(p.getAllTrees().get(0).getRoot().getLabel(), "funcdef");
////        assertEquals(p.getAllTrees().get(1).getRoot().getLabel(), "funcdef");
////        assertEquals(p.getAllTrees().get(2).getRoot().getLabel(), "funcdef");
//
//    }

}
