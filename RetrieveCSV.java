
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

/**@author Stephen Muharsky, William Baker, Brandon Howe
 * @version 1.0
 *
 * This class retrieves data, whether it be from the internet when it retrieves total infections, or from locally hosted CSV files
 *  for the other relevant data points.
 *
 */
public class RetrieveCSV {

    private final String CASES_BY_SEX_PATH = "DataCsv\\CASES_BY_SEX.csv";
    private final String FEMALE_POP_PATH = "DataCsv\\TotalPopFemale.csv";
    private final String MALE_POP_PATH = "DataCsv\\TotalPopMale.csv";
    private final String TOTAL_POP_PATH = "DataCsv\\TotalPop.csv";

    /**
     * This Function retrieves total infections from a JSON file on a website
     * @param country string representing the country name
     * @return double value representing total infections
     * @throws UnknownHostException thrown if there is an invalid internet connection
     */
    public double retrieveTotalInfections(String country) throws UnknownHostException {
        int cases = 0;
        ReadWebsite reader = new ReadWebsite("https://api.covid19api.com/total/dayone/country/%s/status/confirmed");
        try {
            cases = reader.retrieveData("Cases", country);
        } catch (UnknownHostException e) {
            throw new UnknownHostException();
        }
        return cases;
    }

    /**
     * This function gets the cases by sex from a CSV file
     * @param Country string representing the country name
     * @param gender char representing sex. M for male, F for female
     * @return Double value representing the number of cases by sex
     * @throws UnknownHostException thrown if there isnt a valid intenet
     */
    public double getCasesBySex(String Country, char gender) throws UnknownHostException {
        double result = 0;
        int idx;
        if(gender == 'm' || gender == 'M') idx = 5;
        else idx = 6;

        try {
            ReadCSV reader = new ReadCSV(CASES_BY_SEX_PATH);
            String value = reader.findVal(Country, 1, idx);
            String valueShort = "0";
            try {
                valueShort = value.substring(0, value.length() - 1);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Country name irregularity");
            }

            result = Double.parseDouble(valueShort);
            result = result*0.01;
        }
        catch(FileNotFoundException e) {
            System.out.println("not found");
        }
        catch(IOException e) {
            System.out.println("IO");
        }
        result = result * retrieveTotalInfections(Country);
        return(result);
    }
/**
 * @param country
 * String representing the country
 * @param sex
 * M for male F for female T for total
 *
 * */
    public double getPop(String country, char sex) {
        double result = 0;
        String path;
        if(sex == 'm' || sex == 'M') path = MALE_POP_PATH;
        else if (sex == 'T' || sex == 't') path = TOTAL_POP_PATH;
        else path = FEMALE_POP_PATH;

        try {
            ReadCSV reader = new ReadCSV(path);
            String value = reader.findVal(country, 0, 62);
            result = Double.parseDouble(value);
        }
        catch(FileNotFoundException e) {
            System.out.println("not found");
        }
        catch(IOException e) {
            System.out.println("IO");
        }

        return(result);
    }
}
