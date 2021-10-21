import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class ReadWebsite {

    private String URL;

    /**
     * Initalizer function for read website
     * @param URL String representing the website URL
     */
    public ReadWebsite(String URL) {
        this.URL = URL;
    }

    /**
     * Function used to retrieve data from website and read json files
     * @param columnName name of column to be accessed
     * @param countryName String name of country
     * @return integer representing the data
     * @throws UnknownHostException Thrown if invalid internet connection
     */
    public int retrieveData(String columnName, String countryName) throws UnknownHostException {
        String URLString = String.format(URL, countryName);
        int cases = 0;
        try {

            java.net.URL url = new URL(URLString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode == 200) {
                StringBuilder inline = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }

                sc.close();
                JsonArray jsonArray = new JsonParser().parse(inline.toString()).getAsJsonArray();
                int size = jsonArray.size() - 1;
                cases = jsonArray.get(size).getAsJsonObject().get(columnName).getAsInt();
            }
        } catch (IOException e) {
            throw new UnknownHostException();
        }
        return(cases);
    }
}
