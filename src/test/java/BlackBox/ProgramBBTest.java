package BlackBox;

import Loader.Loader;
import Loader.LoaderFactory;
import Program.Program;
import Program.PythonNode;
import Program.PythonProgramFactory;
import Program.PythonTree;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class ProgramBBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();
    LoaderFactory lfactory = new LoaderFactory();

    //test root node returned for single definition program
//    @Test
//    public void test1() throws IOException{
//        String tree1Str1 = "def(data(ass css(boy)) stat)";
//        PythonTree tree1 = pfactory.makePythonTree(tree1Str1);
//
//        assertEquals(tree1.getRoot().getLabel(), "def");
//    }

    // test multiple root nodes returned for multiple definition program
    @Test
    public void test2() throws IOException{
        Loader loader = lfactory.makePythonLoader("examples/medium/4A");
        Program p = loader.Parse();
        PythonTree ptree1 = (PythonTree) p.getAllTrees().get(0);
        PythonTree ptree2 = (PythonTree) p.getAllTrees().get(1);

        assertEquals(ptree1.getRoot().getLabel().equals("main"),true);

        assertEquals(ptree2.getRoot().getLabel().equals("funcdef"),true);
    }

    // test for getAllTrees
//    @Test
//    public void test3() throws IOException{
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse();
//
//        assertEquals(p.getAllTrees().size(), 3);
//    }
//
//    // test for addTree
//    @Test
//    public void test4() throws IOException{
//        String tree1Str1 = "def(data(ass css(boy)) stat)";
//        PythonTree tree1 = pfactory.makePythonTree(tree1Str1);
//
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("../../examples/medium/medium_A_Base.py");
//        p.addTree(tree1);
//
//        PythonTree originalTree = (PythonTree) p.getAllTrees().get(0);
//        PythonTree newTree = (PythonTree) p.getAllTrees().get(1);
//
//        assertEquals(originalTree.getRoot().getLabel() + " " + newTree.getRoot().getLabel(),
//                     "funcdef def");
//    }
//
//    // test for getChildren
//    @Test
//    public void test5() throws IOException{
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("../../examples/medium/medium_A_Split.py");
//        PythonTree ptree0 = (PythonTree) p.getAllTrees().get(0);
//        PythonTree ptree1 = (PythonTree) p.getAllTrees().get(1);
//        PythonTree ptree2 = (PythonTree) p.getAllTrees().get(2);
//        int size1 = ptree0.getRoot().getChildren().size();
//        int size2 = ptree1.getRoot().getChildren().size();
//        int size3 = ptree2.getRoot().getChildren().size();
//
//        assertEquals(size1 + size2 + size3, 6);
//    }
//
//    // test for addChild
//    @Test
//    public void test6() throws IOException{
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("../../examples/simple/simple.py");
//        PythonNode node = new PythonNode(p.getAllTrees().get(0).getRoot().getLabel());
//        PythonNode newNode = pfactory.makePythonNode("funcdef");
//        node.addChild(newNode);
//
//        assertEquals(node.getChildren().size(), 1);
//    }
//
//
//    // test for getLabel
//    @Test
//    public void test7() throws IOException{
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("../../examples/medium/medium_A_Split.py");
//
//        assertEquals(p.getAllTrees().get(2).getRoot().getLabel(), "funcdef");
//    }
//
//    // test for setLabel
//    @Test
//    public void test8() throws IOException{
//        Loader loader = lfactory.makePythonLoader();
//        Program p = loader.Parse("../../examples/medium/medium_A_Split.py");
//        p.getAllTrees().get(1).getRoot().setLabel("simple_stmt");
//
//        assertEquals( p.getAllTrees().get(1).getRoot().getLabel(), "simple_stmt");
//    }

}