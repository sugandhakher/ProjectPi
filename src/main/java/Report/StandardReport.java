package Report;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StandardReport implements Report {
    private Set<Plagiarism> plagiarismSet;
    private Map<String, Integer> statistics;


    public StandardReport(){
        plagiarismSet = new HashSet<Plagiarism>();
        statistics = new HashMap<>();
        statistics.put("pNum", 0);
    }


    @Override
    public Set<Plagiarism> getAllPlagiarism() {
        return plagiarismSet;
    }

    @Override
    public Map<String, Integer> getAllStatistics() {
        return statistics;
    }

    @Override
    public void addStatistics(String name, int value) {
        statistics.put(name, value);
    }

    @Override
    public void addPlagiarism(Plagiarism p) {
        plagiarismSet.add(p);
        int pnum = statistics.get("pNum") + 1;
        statistics.put("pNum", pnum);

    }
    @Override
    public boolean hasPlagiarism(){
        return !plagiarismSet.isEmpty();
    }
}
