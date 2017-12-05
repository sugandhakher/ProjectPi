package tests;

import analysis.*;
import program.PythonProgramFactory;
import loader.PythonLoader;
import loader.LoaderFactory;
import program.Program;

import org.junit.Test;
import report.Plagiarism;
import report.StandardPlagiarism;

import java.io.IOException;
import static org.junit.Assert.*;



public class AnalysisBBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();
    LoaderFactory lfactory = new LoaderFactory();
    AnalysisFactory afactory = new AnalysisFactory();
    private TEDAnalysis preLoad(String path1, String path2) throws IOException{
        PythonLoader loader = lfactory.makePythonLoader(path1);
        PythonLoader loader2 = lfactory.makePythonLoader(path2);
        Program p = loader.Parse();
        Program p2 = loader2.Parse();
        TEDAnalysis A = afactory.makeTEDAnalysis(p,p2);
        return A;
    }

//    @Test
//    public void test1() throws IOException{
//        String tree1Str1 = "def(data(ass css(boy)) stat)";
//        String tree1Str2 = "def(css(data(ass boy)) stat)";
//        PythonTree tree1 = pfactory.makePythonTree(tree1Str1);
//        PythonTree tree2 = pfactory.makePythonTree(tree1Str2);
//        program p1 = pfactory.makePythonProgram();
//        program p2 = pfactory.makePythonProgram();
//        p1.addTree(tree1);
//        p2.addTree(tree2);
//        analysis a = afactory.makeTEDAnalysis(p1,p2);
//        a.runAnalysis();
//        a = (TEDAnalysis)a;
//        assertEquals(((TEDAnalysis) a).getTreeEditDistance(),2);
//    }

    // add a variable in statement
    @Test
    public void test2() throws IOException{
        String projectPath1 = "examples/simple/1A";
        String projectPath2 = "examples/simple/1B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }

    //change position of two variable
    @Test
    public void test3() throws IOException{
        String projectPath1 = "examples/simple/2A";
        String projectPath2 = "examples/simple/2B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
 //       assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }

    //change position of two statement
    @Test
    public void test4() throws IOException{
        String projectPath1 = "examples/simple/3A";
        String projectPath2 = "examples/simple/3B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
       // assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }


    //modify two statements
    @Test
    public void test5() throws IOException{
        String projectPath1 = "examples/simple/4A";
        String projectPath2 = "examples/simple/4B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        //assertEquals(A.getTreeEditDistance(),3);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
//    test upload: change path to upload
//    @Test
//    public void testUpload() throws IOException{
//        loader loader = new PythonLoader();
//        program p = loader.Parse("uploads/python/simple5.py");
//        program p2 = loader.Parse("uploads/python/simple6.py");
//        TEDAnalysis A = new TEDAnalysis(p,p2);
//        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),3);
//    }

    //change functin name
    @Test
    public void funName() throws IOException{
        String projectPath1 = "examples/simple/5A";
        String projectPath2 = "examples/simple/5B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        //assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }

    //simple program split into small functions, currently not support
    @Test
    public void devideSimple() throws IOException{
        String projectPath1 = "examples/simple/6A";
        String projectPath2 = "examples/simple/6B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        //assertEquals(A.getTreeEditDistance(),4);
        assertEquals(A.getReport().hasPlagiarism(),true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num,2);
    }

    @Test
    public void devideSimple2() throws IOException{
        String projectPath1 = "examples/simple/7A";
        String projectPath2 = "examples/simple/7B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(),true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num,1);
    }

///////////////////////Medium////////////////////////////////////////////
    //medium size program test, change names
    @Test
    public void changenameMediumA() throws IOException{
        String projectPath1 = "examples/medium/1A";
        String projectPath2 = "examples/medium/1B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(),true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num,1);
    }
//
//
//
//
//
//    //medium program split into small functions, currently not support
    @Test
    public void devideMedium() throws IOException{
        String projectPath1 = "examples/medium/2A";
        String projectPath2 = "examples/medium/2B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(),true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num,1);

    }
//
//

    @Test
    public void devideMedium2() throws IOException{
        String projectPath1 = "examples/medium/4B";
        String projectPath2 = "examples/medium/4A";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(),true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num,1);

    }


}