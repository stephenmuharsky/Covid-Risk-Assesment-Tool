
/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class ProxyLogin implements Login {
    private Login realLogin;

    /**
     * Proxy isValid function
     * @param username String representing the username
     * @param password String represnening the password
     * @return boolean representing if password username combo is valid
     */
    public boolean isValid(String username, String password)
    {
        if (realLogin == null) {
            realLogin = new RealLogin();
        }
        return(realLogin.isValid(username, password));
    }
}
