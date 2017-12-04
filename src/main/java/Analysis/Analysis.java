package Analysis;

import Report.Report;
/** Interface Analysis
 * Role: Component Interface
 * This component contain our major algorithm of determine if two piece of code is similar. After run the analysis
 *  you will get a report which we defined. And we can implement different algorithm in different analysis
 *  implementation
 */
public interface Analysis{
    /**
     * return the report after running analysis
     */
    public Report getReport();
    /**
     * run compare algorithm
     */
    public void runAnalysis();
}
