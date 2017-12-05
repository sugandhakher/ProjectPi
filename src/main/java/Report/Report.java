package report;

import java.util.Set;
import java.util.Map;

/**
 * Interface report
 * Role: component interface
 * This component is the result of an analysis, it record all plagiarism we found in two programs
 */
public interface Report {

    /**
     * Retrun Plagiarized set of nodes
     */
    public Set<Plagiarism> getAllPlagiarism();

    /**
     * Return map of all statistics
     */
    public Map<String, Integer> getAllStatistics();

    /** Return to unique id of report
     */
//    public int getReportID();
    /** generate a pdf in given location
     * @param : path , the path you would like to save pdf file
     */
    //   public void generatePDF(String path);

    /**
     * Detects the plagiarized statistics
     */
    public void addPlagiarism(Plagiarism p);

    /**
     * add all statistics to the map
     */
    public void addStatistics(String name, int value);

    /**
     * Confirm if programs are plagiarized
     */
    boolean hasPlagiarism();

}
