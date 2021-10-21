import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CRAT {

    /**
     * Main function for the program
     * @param args no args need to be passed
     */
    public static void main(String[] args) {
        UI.DrawLoginUI();
    }

    /**
     * Handles adding the countries to countrylist singleton
     * @param country String representing country name
     * @throws IllegalStateException Invalid country name
     */
    public static void addCountry(String country) throws IllegalStateException {
        CountryListServices listServices = new CountryListServices();
        listServices.addCountry(country);
    }

    /**
     * handles removing countries from the country list singleton
     * @param country String represinging country name
     * @throws IllegalStateException If country is not on the list
     */
    public static void removeCountry(String country) throws IllegalStateException {
        CountryListMgmt listMgmt = new CountryListMgmt();
        listMgmt.removeCountry(country);
    }

    /**
     * Passes the correct int to the analysis handler
     * @param analysisType String value representing the analysis type
     * @return ArrayList containing the results from the analaysis
     * @throws UnknownHostException If invalid internet connection
     */
    public static ArrayList<Double> performAnalysis(String analysisType) throws UnknownHostException {
        int analysisTypeInt;
        switch(analysisType) {
            case "CovidConfirmedCases":
                analysisTypeInt = 0;
                break;

            case "CovidConfirmedCasesMales":
                analysisTypeInt = 1;
                break;

            case "CovidConfirmedCasesFemales":
                analysisTypeInt = 2;
                break;

            case "CovidConfirmedCasesPerCapita":
                analysisTypeInt = 3;
                break;

            case "CovidConfirmedCasesPerCapitaFemales":
                analysisTypeInt = 4;
                break;

            case "CovidConfirmedCasesPerCapitaMales":
                analysisTypeInt = 5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + analysisType);
        }
        AnalysisHandler handler = new AnalysisHandler(analysisTypeInt);
        return(handler.performAnalysis());
    }
}
