import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CountryListSingleton {

    private static List<String> masterCountryList = new ArrayList<>();
    private static List<String> currentCountryList = new ArrayList<>();

    /**
     * This function returns the main list
     * @return List with all valid country names
     */
    public static List<String> getMasterList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("coordinates.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineList = line.split(",");
                masterCountryList.add(lineList[3]);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("not found");
        }
        catch(IOException e) {
            System.out.println("IO");
        }
        return(masterCountryList);
    }

    public static List<String> getCurrentList() {
        return(currentCountryList);
    }
}
