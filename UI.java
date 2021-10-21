import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      William Baker
 * @version     1.0
 * @since       1.0
 *
 * Handles all UI creation, functionality, and methods that respond to button clicks
 */
public class UI {
    /**
     * The window containing all of the map and country elements
     */
    private static JFrame mapFrame;

    /**
     * The text label to show where to add a country
     */
    private static JLabel addCountryLabel;

    /**
     * The text field for the user to type in a country to add
     */
    private static JTextField addCountryText;

    /**
     * The plus button to add the country name from addCountryText
     */
    private static JButton addCountryButton;

    /**
     * The text label to show where to remove a country
     */
    private static JLabel removeCountryLabel;

    /**
     * The text field for the user to type in a country to remove
     */
    private static JTextField removeCountryText;

    /**
     * The minus button to remove the country name from removeCountryText
     */
    private static JButton removeCountryButton;

    /**
     * The image of the world map, used to display the analytical results
     */
    private static JLabel mapImage;

    /**
     * When the map image is drawn, how much to scale down the image
     */
    private static float mapRescaleAmmount = 1.5f;

    /**
     * The text label to show the current list of added countries
     */
    private static JLabel selectedCountriesLabel;

    /**
     * The readonly text field for the current list of added countries
     */
    private static JTextArea selectedCountriesText;

    /**
     * The button to perform the analysis on the selected countries
     */
    private static JButton recalculateButton;

    /**
     * The text label to show the output of the country analysis
     */
    private static JLabel outputLabel;

    /**
     * The readonly text field for the output of the country analysis
     */
    private static JTextArea outputText;

    /**
     * The text label to show analysisTypeDropdown
     */
    private static JLabel analysisTypeLabel;

    /**
     * The dropdown box to allow the user to select one of the six analysis types
     */
    private static JComboBox<String> analysisTypeDropdown;

    /**
     * The window containing all of the login system elements
     */
    private static JFrame loginFrame;

    /**
     * The text label to show the user where to type the username
     */
    private static JLabel usernameLabel;

    /**
     * The text field for the user to type in a username
     */
    private static JTextField usernameText;

    /**
     * The text label to show the user where to type the password
     */
    private static JLabel passwordLabel;

    /**
     * The text field for the user to type in a password
     */
    private static JTextField passwordText;

    /**
     * The button to submit the typed login information
     */
    private static JButton submitButton;

    /**
     * Creates the main User Interface for the program
     * <p>
     * Creates and sets up java swing elements for each part of the UI
     */
    public static void DrawMapUI() {
        // Creates the main window
        mapFrame = new JFrame("CRAT");

        // Creating and setting the size of the add country label
        addCountryLabel = new JLabel("Add a country:");
        addCountryLabel.setBounds(200, 15, 150, 20);
        // Creating and setting the size of the add country text
        addCountryText = new JTextField();
        addCountryText.setBounds(310, 15, 260, 20);
        // Creating and setting the size of the add country button
        addCountryButton = new JButton("+");
        addCountryButton.setBounds(580, 7, 50, 35);
        // Setting the button to call the add function when clicked
        addCountryButton.addActionListener(e -> Add ());

        // Creating and setting the size of the remove country label
        removeCountryLabel = new JLabel("Remove a country:");
        removeCountryLabel.setBounds(750, 15, 150, 20);
        // Creating and setting the size of the remove country text
        removeCountryText = new JTextField();
        removeCountryText.setBounds(870, 15, 260, 20);
        // Creating and setting the size of the remove country button
        removeCountryButton = new JButton("-");
        removeCountryButton.setBounds(1140, 7, 50, 35);
        // Setting the button to call the remove function when clicked
        removeCountryButton.addActionListener(e -> Remove ());

        try {
            // Reads in the map image and gets the dimensions
            BufferedImage image = ImageIO.read(new File("map.jpg"));
            int mapWidth = image.getWidth();
            int mapHeight = image.getHeight();
            // Scales down the map image and stores it in a label
            Image imageResized = image.getScaledInstance((int) (mapWidth / mapRescaleAmmount), (int) (mapHeight / mapRescaleAmmount), Image.SCALE_DEFAULT);
            mapImage = new JLabel(new ImageIcon(imageResized));
            mapImage.setBounds(5, 50, (int) (mapWidth / mapRescaleAmmount), (int) (mapHeight / mapRescaleAmmount));
        } catch (IOException e) {
            System.out.println("Map image not found!");
        }

        // Creating and setting the size of the selected country label
        selectedCountriesLabel = new JLabel("List of selected countries:");
        selectedCountriesLabel.setBounds(1400, 40, 150, 20);
        // Creating and setting the size of the selected country text, and making it readonly
        selectedCountriesText = new JTextArea("");
        selectedCountriesText.setEditable(false);
        selectedCountriesText.setBounds(1380, 70, 195, 380);

        // Creating and setting the size of the recalculate button
        recalculateButton = new JButton("Recalculate");
        recalculateButton.setBounds(1400, 460, 150, 30);
        // Setting the button to call the recalculate function when clicked
        recalculateButton.addActionListener(e -> Recalculate());

        // Creating and setting the size of the output label
        outputLabel = new JLabel("Output:");
        outputLabel.setBounds(1450, 500, 150, 20);
        // Creating and setting the size of the output text, and making it readonly
        outputText = new JTextArea("");
        outputText.setEditable(false);
        outputText.setBounds(1380, 520, 195, 225);

        // Creating and setting the size of the analysis label
        analysisTypeLabel = new JLabel("Choose analysis method:");
        analysisTypeLabel.setBounds(540, 750, 150, 20);
        // The list of strings for each analysis type
        String[] testStrings = {"CovidConfirmedCases", "CovidConfirmedCasesPerCapita", "CovidConfirmedCasesMales", "CovidConfirmedCasesFemales", "CovidConfirmedCasesPerCapitaMales", "CovidConfirmedCasesPerCapitaFemales"};
        // Creating and setting the size of the analysis dropdown, and setting the first option as the selected one
        analysisTypeDropdown = new JComboBox<>(testStrings);
        analysisTypeDropdown.setSelectedIndex(0);
        analysisTypeDropdown.setBounds(710, 750, 300, 20);

        // Setting how the window closes
        mapFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adding each element to the window
        mapFrame.add(addCountryLabel);
        mapFrame.add(addCountryText);
        mapFrame.add(addCountryButton);
        mapFrame.add(removeCountryLabel);
        mapFrame.add(removeCountryText);
        mapFrame.add(removeCountryButton);
        mapFrame.add(mapImage);
        mapFrame.add(selectedCountriesLabel);
        mapFrame.add(selectedCountriesText);
        mapFrame.add(recalculateButton);
        mapFrame.add(outputLabel);
        mapFrame.add(outputText);
        mapFrame.add(analysisTypeLabel);
        mapFrame.add(analysisTypeDropdown);
        // Centering the window and setting it's size
        mapFrame.setLayout(null);
        mapFrame.setBounds(0, 0, 1600, 850);
        mapFrame.setLocationRelativeTo(null);
        // Allowing the user to see the window
        mapFrame.setVisible(true);
    }

    /**
     * Creates the login User Interface for the program
     * <p>
     * Creates and sets up java swing elements for each part of the UI
     */
    public static void DrawLoginUI() {
        // Creates the main window
        loginFrame = new JFrame("Login");

        // Creating and setting the size of the username label
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 100, 20);
        // Creating and setting the size of the username text
        usernameText = new JTextField();
        usernameText.setBounds(100, 10, 200, 20);

        // Creating and setting the size of the password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 100, 20);
        // Creating and setting the size of the password text
        passwordText = new JTextField();
        passwordText.setBounds(100, 40, 200, 20);

        // Creating and setting the size of the submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(110, 80, 100, 30);
        // Setting the button to call the try login function
        submitButton.addActionListener(e -> TryLogin());

        // Setting how the window closes
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adding each element to the window
        loginFrame.add(usernameLabel);
        loginFrame.add(usernameText);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordText);
        loginFrame.add(submitButton);
        // Centering the window and setting it's size
        loginFrame.setLayout(null);
        loginFrame.setBounds(0, 0, 330, 160);
        loginFrame.setLocationRelativeTo(null);
        // Allowing the user to see the window
        loginFrame.setVisible(true);
    }

    /**
     * Tries to add a country to the country list, and gives a error if it fails
     * <p>
     * Called when the add button is pressed
     */
    public static void Add () {
        try {
            // Attempts to add the country to the list
            CRAT.addCountry(addCountryText.getText());
        }
        catch(IllegalStateException a) {
            JOptionPane.showMessageDialog(mapFrame, a.getMessage());
        }
        // Gets the current list of countries
        List<String> currentList = CountryListSingleton.getCurrentList();
        // Clears the selected countries text
        selectedCountriesText.setText("");
        // Goes through each country and adds it to the text field
        for(String i : currentList) {
            selectedCountriesText.append(i + "\n");
        }
        // Clears the add country text field
        addCountryText.setText("");
    }

    /**
     * Tries to remove a country from the country list, and gives a error if it fails
     * <p>
     * Called when the remove button is pressed
     */
    public static void Remove () {
        try {
            // Attempts to remove the country to the list
            CRAT.removeCountry(removeCountryText.getText());
        } catch (IllegalStateException x) {
            JOptionPane.showMessageDialog(mapFrame, "INVALID COUNTRY NAME");
        }
        // Gets the current list of countries
        List<String> currentList = CountryListSingleton.getCurrentList();
        // Clears the selected countries text
        selectedCountriesText.setText("");
        // Goes through each country and adds it to the text field
        for(String i : currentList) {
            selectedCountriesText.append(i + "\n");
        }
        // Clears the remove country text field
        removeCountryText.setText("");
    }

    /**
     * Calculates the analysis information to be displayed
     * <p>
     * Called when the recalculate button is pressed
     * <p>
     * Gets the analysis data from the dropdown, and gets the required analysis data
     * <p>
     * Sets the output text and draws circles on the map corresponding to the analysis data
     */
    public static void Recalculate() {
        try {
            // Makes sure the selected dropdown item exists (it always should be)
            if (analysisTypeDropdown.getSelectedItem() == null) {
                throw new NullPointerException();
            }
            // Gets the selected analysis type from the dropdown
            String analysisType = analysisTypeDropdown.getSelectedItem().toString();
            // Runs the analysis and stores the results
            ArrayList<Double> values = CRAT.performAnalysis(analysisType);
            // Clears the output text
            outputText.setText("");
            // Goes through each value
            for (int i = 0; i < values.size(); i++) {
                // Adds the country and analysis value to the output text
                outputText.append(CountryListSingleton.getCurrentList().get(i) + " =>\n" + "   COVID: " + values.get(i) + "\n");
            }
            // Getting the image again to get the clear map without any circles
            BufferedImage image = ImageIO.read(new File("map.jpg"));
            // Getting the map image dimensions
            int mapWidth = image.getWidth();
            int mapHeight = image.getHeight();
            // Drawing circles on the map to represent the country analysis values
            BufferedImage circlesImage = DisplayResults.DrawCircles(image, mapWidth, mapHeight, values);
            // Scaling the image
            Image imageResized = circlesImage.getScaledInstance((int) (mapWidth / mapRescaleAmmount), (int) (mapHeight / mapRescaleAmmount), Image.SCALE_DEFAULT);
            // Updating the image in the window
            mapImage.setIcon(new ImageIcon(imageResized));
        } catch (UnknownHostException unknownHostException) {
            JOptionPane.showMessageDialog(mapFrame, "INVALID INTERNET CONNECTION");
        } catch (IOException ioException) {
            System.out.println("Map image not found!");
        } catch (NullPointerException nullPointerException) {
            System.out.println("Dropdown item not found!");
        }
    }

    /**
     * Allows the user to type in a username and password
     */
    public static void TryLogin() {
        Login proxy = new ProxyLogin();
        if (proxy.isValid(usernameText.getText(), passwordText.getText())) {
            loginFrame.setVisible(false);
            DrawMapUI();
        }
    }
}