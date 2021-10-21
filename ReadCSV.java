import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**@author Stephen Muharsky, William Baker, Brandon Hower
 * This helper class is used to read the contents of a CSV file in search for a particular country's data point
 */
public class ReadCSV {

    private final String PATH;
    private BufferedReader br;

    /**
     * Initilizer function for the readCSV class
     * @param PATH path to the CSV file
     * @throws FileNotFoundException if the file isnt found
     */
    public ReadCSV(String PATH) throws FileNotFoundException {
        this.PATH = PATH;
        BufferedReader br = new BufferedReader(new FileReader(PATH));
        this.br = br;

    }

    /**
     * Finds a specific value for a country name
     * @param countryName name of country
     * @param countryIdx Index of the country
     * @param resultIdx Index of the result
     * @return String representing the value
     * @throws IOException
     */
    public String findVal(String countryName, int countryIdx, int resultIdx) throws IOException {
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
            String[] lineList = line.split(",");
            if(lineList[countryIdx].equals(countryName)) {
                result = lineList[resultIdx];
                break;
            }
        }
        return result;
    }
}
