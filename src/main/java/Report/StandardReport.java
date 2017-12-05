package report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Standard Report that returns the plagiarized report
 */
public class StandardReport implements Report {

    private Set<Plagiarism> plagiarismSet;
    private Map<String, Integer> statistics;


    /**
     * Default constructor
     */
    public StandardReport() {
        plagiarismSet = new HashSet<Plagiarism>();
        statistics = new HashMap<>();
        statistics.put("pNum", 0);
    }


    /**
     * Retrun Plagiarized set of nodes
     */
    @Override
    public Set<Plagiarism> getAllPlagiarism() {
        return plagiarismSet;
    }

    /**
     * Return map of all statistics
     */
    @Override
    public Map<String, Integer> getAllStatistics() {
        return statistics;
    }

    /**
     * add all statistics to the map
     */
    @Override
    public void addStatistics(String name, int value) {
        statistics.put(name, value);
    }

    /**
     * Detects the plagiarized statistics
     */
    @Override
    public void addPlagiarism(Plagiarism p) {
        plagiarismSet.add(p);
        int pnum = statistics.get("pNum") + 1;
        statistics.put("pNum", pnum);

    }

    /**
     * Confirm if programs are plagiarized
     */
    @Override
    public boolean hasPlagiarism() {
        return !plagiarismSet.isEmpty();
    }
}
