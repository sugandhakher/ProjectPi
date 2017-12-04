package PlagiarismDetectorAPI;

import Report.Report;
/** Interface PlagiarismDetector
 * Role: Program Interface
 *  This is the API we provided to use our program
 */
public interface PlagiarismDetector extends Report {
    /** Return to a report after our analysis
     * @param pathOrigin: the path for origin python project
     * @param pathOrigin: the path for compare python project
     */
    public Report compareProgram(String pathOrigin, String pathCompare);
    /** return to a string of path of generated PDF of report
     */
    public String getReportPDF();
}
