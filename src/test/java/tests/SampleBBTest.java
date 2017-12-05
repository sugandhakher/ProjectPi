package tests;

import analysis.TEDAnalysis;
import program.Program;
import org.junit.Test;
import program.*;
import loader.*;
import analysis.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SampleBBTest {
    PythonProgramFactory pfactory = new PythonProgramFactory();
    LoaderFactory lfactory = new LoaderFactory();
    AnalysisFactory afactory = new AnalysisFactory();
    private TEDAnalysis preLoad(String path1, String path2) throws IOException{
        PythonLoader loader = lfactory.makePythonLoader(path1);
        PythonLoader loader2 = lfactory.makePythonLoader(path2);
        Program p = loader.Parse2();
        Program p2 = loader2.Parse2();
        TEDAnalysis A = afactory.makeTEDAnalysis(p,p2);
        return A;
    }
    @Test
    public void sampleSet1Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set01/A";
        String projectPath2 = "examples/PythonSamples/set01/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
    @Test
    public void sampleSet2Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set02/A";
        String projectPath2 = "examples/PythonSamples/set02/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }

    @Test
    public void sampleSet3Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set03/A";
        String projectPath2 = "examples/PythonSamples/set03/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),false);
    }
    @Test
    public void sampleSet4Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set04/A";
        String projectPath2 = "examples/PythonSamples/set04/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
    @Test
    public void sampleSet5Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set05/A";
        String projectPath2 = "examples/PythonSamples/set05/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),true);
    }
    //do not have plagiarism
    @Test
    public void sampleSet2Set4Test() throws IOException {
        String projectPath1 = "examples/PythonSamples/set02/A";
        String projectPath2 = "examples/PythonSamples/set04/B";
        TEDAnalysis A = preLoad(projectPath1,projectPath2);
        A.runAnalysis();
//        assertEquals(A.getTreeEditDistance(),0);
        assertEquals(A.getReport().hasPlagiarism(),false);
    }
}
