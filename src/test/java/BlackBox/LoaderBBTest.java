package BlackBox;
import Analysis.*;
import Program.PythonProgramFactory;
import Loader.Loader;
import Loader.PythonLoader;
import Program.Program;
import Program.PythonTree;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import Loader.LoaderFactory;

public class LoaderBBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();
    LoaderFactory lfactory = new LoaderFactory();
//    @Test
//    public void test1() throws IOException{
//        PythonLoader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("examples/simple/simple.py");
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
//        Program p = loader.Parse("examples/medium/medium_A_Split.py");
//        assertEquals(p.getAllTrees().get(0).getRoot().getLabel(), "funcdef");
//        assertEquals(p.getAllTrees().get(1).getRoot().getLabel(), "funcdef");
//        assertEquals(p.getAllTrees().get(2).getRoot().getLabel(), "funcdef");
//
//    }
//
//    @Test
//    public void test3() throws IOException{
//        PythonLoader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("examples/simple/simpleSplitB.py");
////        assertEquals(p.getAllTrees().get(0).getRoot().getLabel(), "funcdef");
////        assertEquals(p.getAllTrees().get(1).getRoot().getLabel(), "funcdef");
////        assertEquals(p.getAllTrees().get(2).getRoot().getLabel(), "funcdef");
//
//    }

}
