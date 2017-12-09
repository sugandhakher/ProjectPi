package api.Services;

import analysis.AnalysisFactory;
import analysis.TEDAnalysis;
import loader.LoaderFactory;
import loader.PythonLoader;
import program.Program;
import program.PythonProgramFactory;
import report.Report;

import java.io.IOException;

public class AnalysisService {
    public static Report analysis(String path1, String path2) throws IOException {
        LoaderFactory lfactory = new LoaderFactory();
        AnalysisFactory afactory = new AnalysisFactory();

        PythonLoader loader1 = lfactory.makePythonLoader(path1);
        PythonLoader loader2 = lfactory.makePythonLoader(path2);
        Program p = loader1.parse();
        Program p2 = loader2.parse();
        TEDAnalysis A = afactory.makeTEDAnalysis(p, p2);
        A.runAnalysis();

        return A.getReport();

    }
}
