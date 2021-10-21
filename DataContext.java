import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class DataContext {

    /**
     * function used for rounding doubles
     * @param d Double to be rounded
     * @param places Number of decimal places desired
     * @return The rounded double value
     */
    private double roundDouble(double d, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * This function handles getting data from the data strategy
     * @param country String representing the country name
     * @param analysisType Int representing analysis type
     * @return double with the result
     * @throws UnknownHostException If invalid internet connection
     */
    public double contextGetData(String country, int analysisType) throws UnknownHostException {
        RetrieveCSV data = new RetrieveCSV();
        double returnVal =0;
        switch(analysisType) {
            case 0: // Total Covid Case
                returnVal = data.retrieveTotalInfections(country);
                break;

            case 1: // Total Male Case
               returnVal = data.getCasesBySex(country, 'm');
               returnVal = roundDouble(returnVal, 0);
               break;

            case 2: // Total Female cases
                returnVal = data.getCasesBySex(country, 'f');
                returnVal = roundDouble(returnVal, 0);
                break;

            case 3: // Per capita
                double infections = data.retrieveTotalInfections(country);
                double pop = data.getPop(country, 't');
                returnVal = infections/pop;
                returnVal = roundDouble(returnVal, 4);
                break;

            case 4: // Per  capita female
                double femaleInfections = data.getCasesBySex(country, 'f');
                double femalePop = data.getPop(country, 'f');
                returnVal = femaleInfections / femalePop;
                returnVal = roundDouble(returnVal, 4);
                break;

            case 5: // per capita male
                double maleInfections = data.getCasesBySex(country, 'm');
                double malePop = data.getPop(country, 'm');
                returnVal = maleInfections / malePop;
                returnVal = roundDouble(returnVal, 4);
                break;
        }
        return returnVal;
    }

}
