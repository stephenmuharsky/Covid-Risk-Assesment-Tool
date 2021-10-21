import java.util.List;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CountryListServices {

    /**
     * Adds country to countrylist singleton
     * @param country String country name to be added
     * @throws IllegalStateException If country name invalid
     */
    public void addCountry(String country) throws IllegalStateException {
        List<String> masterList = CountryListSingleton.getMasterList();
        if(masterList.contains(country) && !CountryListSingleton.getCurrentList().contains(country)) {
            CountryListSingleton.getCurrentList().add(country);
        }
        else if (!CountryListSingleton.getCurrentList().contains(country)){
            throw new IllegalStateException("Invalid country name");
        }
        else {
            throw new IllegalStateException("Country already added");
        }
    }
}
