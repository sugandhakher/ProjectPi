package blackBoxTests;

import analysis.*;
import program.PythonProgramFactory;
import loader.PythonLoader;
import loader.LoaderFactory;
import program.Program;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AnalysisBBTest {

    LoaderFactory lfactory = new LoaderFactory();
    AnalysisFactory afactory = new AnalysisFactory();

    private TEDAnalysis preLoad(String path1, String path2) throws IOException {
        PythonLoader loader = lfactory.makePythonLoader(path1);
        PythonLoader loader2 = lfactory.makePythonLoader(path2);
        Program p = loader.parse();
        Program p2 = loader2.parse();
        TEDAnalysis A = afactory.makeTEDAnalysis(p, p2);
        return A;
    }

    // add a variable in statement
    @Test
    public void test1() throws IOException {
        String projectPath1 = "examples/simple/1A";
        String projectPath2 = "examples/simple/1B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
    }

    //change position of two variable
    @Test
    public void test2() throws IOException {
        String projectPath1 = "examples/simple/2A";
        String projectPath2 = "examples/simple/2B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
    }

    //change position of two statement
    @Test
    public void test3() throws IOException {
        String projectPath1 = "examples/simple/3A";
        String projectPath2 = "examples/simple/3B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
    }

    //modify two statements
    @Test
    public void test4() throws IOException {
        String projectPath1 = "examples/simple/4A";
        String projectPath2 = "examples/simple/4B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
    }

    //change function name
    @Test
    public void funName() throws IOException {
        String projectPath1 = "examples/simple/5A";
        String projectPath2 = "examples/simple/5B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
    }
}