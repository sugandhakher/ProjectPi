package Report;

import java.util.Set;
import java.util.Map;
/** Interface Report
 * Role: component interface
 *  This component is the result of an analysis, it record all plagiarism we found in two programs
 */
public interface Report{
    /** Return to a set of plagiarism, which are all plagiarisms we found
     */
    public Set<Plagiarism> getAllPlagiarism();
    /** Return to a overall statistics, key of map is the name of this statistics, value is the numeric measure
     */
    public Map<String, Integer> getAllStatistics();
    /** Return to unique id of report
     */
//    public int getReportID();
    /** generate a pdf in given location
     * @param : path , the path you would like to save pdf file
     */
 //   public void generatePDF(String path);

    public void addPlagiarism(Plagiarism p);

    public void addStatistics(String name, int value);

    boolean hasPlagiarism();

}
