package whiteBoxTests;

import analysis.AnalysisFactory;
import analysis.TEDAnalysis;
import loader.LoaderFactory;
import loader.PythonLoader;
import org.junit.Test;
import program.*;
import report.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReportWBTest {

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

    // Get Plagiarised Sets
    @Test
    public void plagSet() throws IOException {
        String projectPath1 = "examples/simple/5A";
        String projectPath2 = "examples/simple/5B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        Plagiarism p2 = null;
        for (Plagiarism p : A.getReport().getAllPlagiarism()) {
            assertTrue(p.getOriginPath().equals("funNameA.py"));
            assertTrue(p.getComparePath().equals("funNameB.py"));
            assertTrue(p.getOriginStartline() == 0 || p.getOriginStartline() == 4);
            assertTrue(p.getOriginEndline() == 5 || p.getOriginEndline() == 0);
            assertTrue(p.getCompareStartline() == 4 || p.getCompareStartline() == 0);
            assertTrue(p.getCompareEndline() == 5 || p.getCompareEndline() == 0);

            p2 = p;
        }
        p2.setConfident(0);
        assertEquals(p2.getConfident(), 0);

    }

    // Test confidence
    @Test
    public void testConfidence() throws IOException {
        String projectPath1 = "examples/medium/2A";
        String projectPath2 = "examples/medium/2B";
        TEDAnalysis A = preLoad(projectPath1, projectPath2);
        A.runAnalysis();
        assertEquals(A.getConfidence(), 0);
    }
}
