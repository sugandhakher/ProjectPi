package report;


/**
 * Interface Plagiarism
 * Role: data structure interface
 * This data structure used to record one plagiarism, one plagiarism should have two maps
 * which indicates the location of code in origin and compare project, and a confident index and type of plagiarism
 */
public interface Plagiarism {
    /** Return to the type of plagiarism
     */
    //   public PTYPE getPlagiarismTypeName();
    /** Return to a map indicates the location of suspect code in origin file, key is file path, value is lines
     */

    //   public Map<String, Integer []> getOriginCode();
    /** Return to a map indicates the location of suspect code in compare file, key is file path, value is lines
     */
    //   public Map<String, Integer []> getCompareCode();

    /**
     * Return the confident as to how much are the two programs similar
     */
    public int getConfident();

    /**
     * Set the confident to the given value
     */
    public void setConfident(int c);

    /**
     * Return the path of original file to compare
     */
    String getOriginPath();

    /**
     * Return the path of comparison file to compare
     */
    String getComparePath();

    /**
     * Return the start line of the Origin Program
     */
    int getOriginStartline();

    /**
     * Return the end line of the Origin Program
     */
    int getOriginEndline();

    /**
     * Return the start line of the Comparison Program
     */
    int getCompareStartline();

    /**
     * Return the end line of the Comparison Program
     */
    int getCompareEndline();

    /**
     * Assign path and start and end line of the original program
     */
    public void addToOrigin(String path, int startline, int endline);

    /**
     * Assign path and start and end line of the comparison program
     */
    public void addToCompare(String path, int startline, int endline);
}
