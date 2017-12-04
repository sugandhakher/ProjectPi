package Report;

import Program.PythonProgram;

import java.util.HashMap;
import java.util.Map;

public class StandardPlagiarism implements Plagiarism{

    private String originPath;
    private String comparePath;
    private int originStartline;
    private int originEndline;
    private int compareEndline;
    private int compareStartline;
    private  int confident;

    @Override
    public String getOriginPath() {
        return originPath;
    }
    @Override
    public String getComparePath() {
        return comparePath;
    }
    @Override
    public int getOriginStartline() {
        return originStartline;
    }
    @Override
    public int getOriginEndline() {
        return originEndline;
    }
    @Override
    public int getCompareStartline() {
        return compareStartline;
    }
   @Override
    public int getCompareEndline() {
        return compareEndline;
    }

    @Override
    public int getConfident() {
        return confident;
    }

    @Override
    public void setConfident(int c) {
        confident = c;
    }



//    public StandardPlagiarism(){
//        originCodeMap = new HashMap<String, Integer[]>();
//        compareCodeMap = new HashMap<String, Integer[]>();
//        confident = 0;
//    }



    @Override
    public void addToOrigin(String path, int startline, int endline) {
       originPath = path;
       originStartline = startline;
       originEndline = endline;

    }

    @Override
    public void addToCompare(String path, int startline, int endline) {
        comparePath = path;
        compareStartline = startline;
        compareEndline = endline;

    }


}
