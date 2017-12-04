package Report;


import java.util.Set;
import java.util.Map;
/** Interface Plagiarism
 * Role: data structure interface
 *  This data structure used to record one plagiarism, one plagiarism should have two maps
 *  which indicates the location of code in origin and compare project, and a confident index and type of plagiarism
 */
public interface Plagiarism  {
    /** Return to the type of plagiarism
     */
 //   public PTYPE getPlagiarismTypeName();
    /** Return to a map indicates the location of suspect code in origin file, key is file path, value is lines
     */

 //   public Map<String, Integer []> getOriginCode();
    /** Return to a map indicates the location of suspect code in compare file, key is file path, value is lines
     */
 //   public Map<String, Integer []> getCompareCode();

    /** getter and setter to confident we think of this result
     */
    public int getConfident();
    public void setConfident(int c);

    String getOriginPath();
    String getComparePath();

    int getOriginStartline();
    int getOriginEndline();
    int getCompareStartline();
    int getCompareEndline();

    public void addToOrigin(String path, int startline, int endline);
    public void addToCompare(String path, int startline, int endline);
}
