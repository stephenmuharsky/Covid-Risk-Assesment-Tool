import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CovidCasePerCapitaFemaleAnalysis implements Analysis{
    private int analysisType = 4;

    /**
     * Uses data context to get the values for all the countries in the singleton list
     * @return Array list of per capita female covid cases
     * @throws UnknownHostException if invalid internet connection
     */
    public ArrayList<Double> performAnalysis() throws UnknownHostException {
        ArrayList<Double> returnval = new ArrayList<>();
        DataContext context = new DataContext();
        for (String i : CountryListSingleton.getCurrentList()) {
            returnval.add(context.contextGetData(i, analysisType));
        }
        return (returnval);
    }
}
