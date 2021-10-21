import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * @author      Brandon Howe
 * @author      Stephen Muharsky
 * @author      Issac Baker
 * @version     1.0
 * @since       1.0
 */
public class DisplayResults {
    /**
     * The filepath for the excel file containing the coordinates
     */
    private static final String COORDINATES_PATH = "coordinates.csv";

    /**
     * Uses the provided map image and country coordinates to draw circles
     * <p>
     * Takes the country analysis data and draws red circles on each country in the list,
     * with a larger circle for a large values and returns the modified image
     *
     * @param  mapImage The unmodified imape of the world map to be modified
     * @param  mapWidth The width of the map, in pixels
     * @param  mapHeight The height of the map, in pixels
     * @param  values The list of analysis values for each country
     * @return the modified image with circles now drawn on it
     */
    public static BufferedImage DrawCircles(BufferedImage mapImage, int mapWidth, int mapHeight, ArrayList<Double> values) {

        // Stores the highest value in the analysis data
        double maxValue = 0;
        // Going through each value to determine the highest
        for (Double value : values) {
            if (maxValue < value) {
                maxValue = value;
            }
        }

        // If the max value is still 0 for any reason, making it not zero to avoid dividing by zero
        if (maxValue == 0) {
            maxValue = 1;
        }

        // Calculating the largest size a circle can be based on the max value
        int maxOvalDimension;
        if (maxValue < 10000)
            maxOvalDimension = 20;
        else if (maxValue < 50000)
            maxOvalDimension = 30;
        else if (maxValue < 100000)
            maxOvalDimension = 50;
        else
            maxOvalDimension = 70;

        // Setting the smallest size a circle can be
        int minOvalDimension = 15;

        try {
            // Get the coordinates excel file data
            BufferedReader br = new BufferedReader(new FileReader(COORDINATES_PATH));
            // Get the graphics data from the map to be able to draw the circles on it
            Graphics2D editableImage = (Graphics2D) mapImage.getGraphics();
            // Go through the excel file line by line
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by each comma
                String[] lineList = line.split(",");
                // If the country list contains a country from the coordinates file
                if(CountryListSingleton.getCurrentList().contains(lineList[3])) {
                    // Get the latitude and longitude from the file for the corresponding country
                    double latitude = Double.parseDouble(lineList[1]);
                    double longitude = Double.parseDouble(lineList[2]);
                    // Storing the coordinates
                    Point2D coords = new Point2D.Double(latitude, longitude);
                    // Use the value of the country to calculate the size of the circle
                    double percentage = values.get(CountryListSingleton.getCurrentList().indexOf(lineList[3])) / maxValue;
                    int ovalDimension = (int)Math.round(((maxOvalDimension - minOvalDimension) * percentage) + minOvalDimension);
                    // Convert the latitude and longitude of the country into pixel coordinates
                    Point circlePos = getXY(coords.getX(), coords.getY(), mapWidth, mapHeight);
                    // Drawing the circles
                    editableImage.setColor(Color.RED);
                    editableImage.setStroke(new BasicStroke(3));
                    editableImage.fillOval(circlePos.x - (ovalDimension / 2), circlePos.y - (ovalDimension / 2), ovalDimension, ovalDimension);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("coordinates file not found");
        }
        catch(IOException e) {
            System.out.println("IO error");
        }
        return mapImage;
    }

    /**
     * Calculates the pixel position of a country
     * <p>
     * Takes the latitude and longitude coordinates of a country and calculates
     * the pixel position of that country on the map image
     *
     * @param  lat The latitude coordinate of the country
     * @param  lng The longitude coordinate of the country
     * @param  mapWidth The width of the map, in pixels
     * @param  mapHeight The height of the map, in pixels
     * @return the x and y pixel coordinates of the country
     */
    public static Point getXY(double lat, double lng, int mapWidth, int mapHeight) {
        int screenX = (int) Math.round((((lng + 180) / 360) * mapWidth));
        int screenY = (int) Math.round(((((lat * -1) + 90) / 180) * mapHeight));
        return new Point(screenX, screenY);
    }
}