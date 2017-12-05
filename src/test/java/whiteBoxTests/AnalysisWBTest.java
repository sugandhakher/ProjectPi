package whiteBoxTests;

import analysis.AnalysisFactory;
import analysis.TEDAnalysis;
import loader.LoaderFactory;
import loader.PythonLoader;
import org.junit.Test;
import program.Program;
import program.PythonProgramFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class AnalysisWBTest {

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

    //simple program split into small functions, currently not support
    @Test
    public void divideSimple() throws IOException {
        String projectPath1 = "examples/simple/6A";
        String projectPath2 = "examples/simple/6B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num, 2);
    }

    @Test
    public void divideSimple2() throws IOException {
        String projectPath1 = "examples/simple/7A";
        String projectPath2 = "examples/simple/7B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num, 1);
    }

    ///////////////////////Medium////////////////////////////////////////////
    //medium size program test, change names
    @Test
    public void changeNameMediumA() throws IOException {
        String projectPath1 = "examples/medium/1A";
        String projectPath2 = "examples/medium/1B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num, 1);
    }

    //medium program split into small functions, currently not support
    @Test
    public void divideMedium() throws IOException {
        String projectPath1 = "examples/medium/2A";
        String projectPath2 = "examples/medium/2B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num, 1);

    }

    @Test
    public void divideMedium2() throws IOException {
        String projectPath1 = "examples/medium/4B";
        String projectPath2 = "examples/medium/4A";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getReport().hasPlagiarism(), true);
        int num = A.getReport().getAllStatistics().get("pNum");
        assertEquals(num, 1);
    }
}