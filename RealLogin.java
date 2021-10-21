import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 */
public class RealLogin implements Login {
    private final String LOGINS_PATH = "Logins.txt";

    /**
     * Validates Username password combo
     * @param username String representing the username
     * @param password String represnening the password
     * @return boolean value representing if the combo is valid
     */
    public boolean isValid(String username, String password) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(LOGINS_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                String[] login = line.split(",");
                if (login[0].equals(username) && login[1].equals(password)) {
                    return true;
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("IO error");
        }
        return false;
    }
}
