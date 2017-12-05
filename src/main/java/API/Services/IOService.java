package api.Services;

import analysis.AnalysisFactory;
import analysis.TEDAnalysis;
import loader.LoaderFactory;
import loader.PythonLoader;
import program.PythonProgramFactory;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import report.*;
import program.*;


public class IOService {

    public static void writeFiles(MultipartFile[] files, String path) throws IOException {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

        for (MultipartFile f : files) {
            String filename = f.getOriginalFilename();
            String filepath = Paths.get(path, filename).toString();
            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(f.getBytes());
            stream.close();
        }
    }


    public static Report analysis(String path1, String path2) throws IOException {
        PythonProgramFactory pfactory = new PythonProgramFactory();
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
