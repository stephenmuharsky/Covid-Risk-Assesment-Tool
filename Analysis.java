import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Analysis interface, used by all analysis classes
 */
public interface Analysis {
    /**
     * This class represents the
     * @return returns an array list containing the results from the analysis
     * @throws UnknownHostException If there is an invalid internet connection
     */
    ArrayList<Double> performAnalysis() throws UnknownHostException;
}
