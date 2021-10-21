import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class TotalCovidCaseAnalysis implements Analysis {
    private int analysisType = 0;

    /**
     * Performs total covid case analysis
     * @return Array list of values found
     * @throws UnknownHostException Thrown if no internet connection
     */
    public ArrayList<Double> performAnalysis() throws UnknownHostException {
        ArrayList<Double> returnval = new ArrayList<>();
        DataContext context = new DataContext();
        for(String i : CountryListSingleton.getCurrentList()) {
            returnval.add(context.contextGetData(i, analysisType));
        }
        return(returnval);
    }
}
