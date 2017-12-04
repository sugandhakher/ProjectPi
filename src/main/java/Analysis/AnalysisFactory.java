package Analysis;
import Program.*;

/** analysis factory
 */
public class AnalysisFactory {
    public TEDAnalysis makeTEDAnalysis(Program origin, Program compare)
    {return new TEDAnalysis(origin, compare);}
}
