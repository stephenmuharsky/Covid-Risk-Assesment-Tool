import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CovidCaseMaleAnalysis implements Analysis{
    private int analysisType = 1;

    /**
     * Uses data context to get the values for all the countries in the singleton list
     * @return Array list of male covid cases
     * @throws UnknownHostException if invalid internet connection
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
