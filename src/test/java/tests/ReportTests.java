package tests;

import analysis.AnalysisFactory;
import analysis.TEDAnalysis;
import loader.LoaderFactory;
import loader.PythonLoader;
import org.junit.Test;
import program.Program;
import program.PythonProgramFactory;
import report.Plagiarism;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReportTests {
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

    // Get Plagiarised Sets
    @Test
    public void plagSet1() throws IOException {
        String projectPath1 = "examples/simple/5A";
        String projectPath2 = "examples/simple/5B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
        Plagiarism p = (Plagiarism) A.getReport().getAllPlagiarism().toArray()[0];
        assertEquals(p.getOriginPath(), "funNameA.py");
        assertEquals(p.getOriginStartline(), 4);
        assertEquals(p.getOriginEndline(), 5);
    }

    // Get Plagiarised Sets
    @Test
    public void plagSet2() throws IOException{
        String projectPath1 = "examples/simple/6A";
        String projectPath2 = "examples/simple/6B";
        TEDAnalysis A2 = preLoad(projectPath1,projectPath2);
        A2.runAnalysis();
        Plagiarism p = (Plagiarism) A2.getReport().getAllPlagiarism().toArray()[1];
        assertEquals(p.getComparePath(), "simpleSplitB.py");
        assertEquals(p.getCompareStartline(), 4);
        assertEquals(p.getCompareEndline(), 6);
        p.setConfident(0);
        assertEquals(p.getConfident(), 0);
    }

    // Test confidence
    @Test
    public void testConfidence() throws IOException{
        String projectPath1 = "examples/medium/2A";
        String projectPath2 = "examples/medium/2B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();

        assertEquals(A.getConfidence(), 0);
    }
}
