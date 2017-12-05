package report;

public class StandardPlagiarism implements Plagiarism {

    /**
     * Variables:
     */
    private String originPath;
    private String comparePath;
    private int originStartline;
    private int originEndline;
    private int compareEndline;
    private int compareStartline;
    private int confident;

    /**
     * Return the path of original file to compare
     */
    @Override
    public String getOriginPath() {
        return originPath;
    }

    /**
     * Return the path of comparison file to compare
     */
    @Override
    public String getComparePath() {
        return comparePath;
    }

    /**
     * Return the start line of the Origin Program
     */
    @Override
    public int getOriginStartline() {
        return originStartline;
    }

    /**
     * Return the end line of the Origin Program
     */
    @Override
    public int getOriginEndline() {
        return originEndline;
    }

    /**
     * Return the start line of the Comparison Program
     */
    @Override
    public int getCompareStartline() {
        return compareStartline;
    }

    /**
     * Return the end line of the Comparison Program
     */
    @Override
    public int getCompareEndline() {
        return compareEndline;
    }

    /**
     * Return the confident as to how much are the two programs similar
     */
    @Override
    public int getConfident() {
        return confident;
    }

    /**
     * Set the confident to the given value
     */
    @Override
    public void setConfident(int c) {
        confident = c;
    }


//    public StandardPlagiarism(){
//        originCodeMap = new HashMap<String, Integer[]>();
//        compareCodeMap = new HashMap<String, Integer[]>();
//        confident = 0;
//    }


    /**
     * Assign path and start and end line of the original program
     */
    @Override
    public void addToOrigin(String path, int startline, int endline) {
        originPath = path;
        originStartline = startline;
        originEndline = endline;
    }

    /**
     * Assign path and start and end line of the comparison program
     */
    @Override
    public void addToCompare(String path, int startline, int endline) {
        comparePath = path;
        compareStartline = startline;
        compareEndline = endline;
    }


}
