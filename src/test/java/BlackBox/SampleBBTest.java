package BlackBox;

import Analysis.TEDAnalysis;
import Loader.Loader;
import Program.Program;
import org.junit.Test;
import Program.*;
import Loader.*;
import Analysis.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SampleBBTest {
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
    @Test
    public void sampleSet2Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set02";
        String projectPath2 = "examples/PythonSamples/set02";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }

    @Test
    public void sampleSet3Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set03";
        String projectPath2 = "examples/PythonSamples/set03";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
    @Test
    public void sampleSet4Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set04";
        String projectPath2 = "examples/PythonSamples/set04";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
    @Test
    public void sampleSet5Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set05";
        String projectPath2 = "examples/PythonSamples/set05";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
}
