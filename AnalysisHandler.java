import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class AnalysisHandler implements Analysis{

    private final int analysisType;

    /**
     * Constructor for the AnalysisHandler class
     * @param analysisType int representing analysis type.
     *                     0 for total covid cases
     *                     1 for total male cases
     *                     2 for total female cases
     *                     3 for per captia cases
     *                     4 for per capita female cases
     *                     5 for per capita male cases
     */
    public AnalysisHandler(int analysisType) {
        this.analysisType = analysisType;
    }

    /**
     * This function handles the analysis type, and calls the correct class based on the kind of analysis used
     * @return Array list with the results
     * @throws UnknownHostException If there is an invalid internet connection
     */
    public ArrayList<Double> performAnalysis() throws UnknownHostException {
        ArrayList<Double> returnval = new ArrayList<>();

        switch(analysisType) {
            case 0: // Total covid cases
                Analysis analysis = new TotalCovidCaseAnalysis();
                returnval = analysis.performAnalysis();
                break;
            case 1: // total male cases
                Analysis analysisM = new CovidCaseMaleAnalysis();
                returnval = analysisM.performAnalysis();
                break;
            case 2: // total female cases
                Analysis analysisF = new CovidCaseFemaleAnalysis();
                returnval = analysisF.performAnalysis();
                break;
            case 3: // percapita cases
                Analysis analysisP = new CovidCasePerCapitaAnalysis();
                returnval = analysisP.performAnalysis();
                break;
            case 4: // per capita female cases
                Analysis analysisPF = new CovidCasePerCapitaFemaleAnalysis();
                returnval = analysisPF.performAnalysis();
                break;
            case 5: // per capita male cases
                Analysis analysisPM = new CovidCasePerCapitaMaleAnalysis();
                returnval = analysisPM.performAnalysis();
                break;
        }
        return(returnval);
    }
}
