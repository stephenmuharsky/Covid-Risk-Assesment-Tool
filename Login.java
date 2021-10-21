
/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public interface Login {

    /**
     * Validates a username password pair
     * @param username String representing the username
     * @param password String represnening the password
     * @return boolean value representing if the username password pair was valid
     */
    boolean isValid(String username, String password);
}
