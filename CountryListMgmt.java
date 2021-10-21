/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class CountryListMgmt {

    /**
     * handles removing a country from the country list singleton
     * @param country String country name
     * @throws IllegalStateException if country name is not on list
     */
    public void removeCountry(String country) throws IllegalStateException{
        if (CountryListSingleton.getCurrentList().contains(country)) {
            CountryListSingleton.getCurrentList().remove(country);
        } else {
            throw new IllegalStateException();
        }
    }
}
