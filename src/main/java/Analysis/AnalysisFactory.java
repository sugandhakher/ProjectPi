package analysis;
import program.*;

/** analysis factory that creates object for TEDAnalysis class
 */
public class AnalysisFactory {

    /**
     * Return object of TEDAnalysis given the two comparison programs
     */
    public TEDAnalysis makeTEDAnalysis(Program origin, Program compare) {
        return new TEDAnalysis(origin, compare);
    }
}
