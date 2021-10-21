//imports for API request
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//imports for JSONs
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

//Imports for Excel
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//other
import java.math.BigDecimal;
import java.math.RoundingMode;

/** @author Stephen Muharsky, William Baker, Brandon Howe
 *
 *  This is a legacy class that was converted to Read/ Retrieve CSV. No longer used.
 *
 */
 public class Retrieve
{
    //method to remove last character
    private static String removeLastChar(String s)
    {
    //returns the string after removing the last character
        return s.substring(0, s.length() - 1);
    }

    //method that returns an int
    private static int intMaker(double d){
        int result = (int) d;
        return result;
    }

    private static double roundDouble(double d, int places) {
   	    BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
   	    bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
   	    return bigDecimal.doubleValue();
   	}

    private static int findRow(XSSFSheet sheet, String cellContent)
    {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                        int result = row.getRowNum();
                        return result;
                    }
                }
            }
        }
        return 0;
    }

    /*/
    private static double retrieveNumberExcel(int sheet, int Row, int Column){
        int result = wb.getSheetAt(sheet).getRow(Row).getCell(Column);
        return result;
    }
    / */


    private static double retrieveTotalInfections(String country) {

        String urlString = String.format("https://api.covid19api.com/total/dayone/country/%s/status/confirmed", country);

        //System.out.println(urlString);

        int cases = 0;

        try {

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode == 200) {

                String inline = "";

                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {

                    inline += sc.nextLine();

                }

                sc.close();

                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();

                int size = jsonArray.size() - 1;

                cases = jsonArray.get(size).getAsJsonObject().get("Cases").getAsInt();

                //System.out.println("Cases: " + cases);

            }



        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return cases;

    }

    private static int retrieveTotalInfectionsMale (String country, double totalInfections) {
        {
            {
                try {
                    File file = new File("C:\\Users\\smuha\\group11\\data\\CASES_BY_SEX.xlsx"); //creating a new fil
                    FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                    //creating Workbook instance that refers to .xlsx file
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
                    Iterator<Row> itr = sheet.iterator();    //iterating over excel file


                    //row of country
                    int countryRow = findRow(sheet, country);

                    //in this particular excel the results we're looking for are always in the 7th column
                    int countryColumn = 5;

                    Cell cell = sheet.getRow(countryRow).getCell(countryColumn);

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                            //System.out.println("A");
                            String contents = cell.getStringCellValue();
                            if(contents.endsWith("%")){
                                String number = removeLastChar(contents);
                                //System.out.println(number);
                                double d = Double.parseDouble(number);
                                d = d / 100.0;
                                double maleInfectionsCalc = d * totalInfections;
                                int maleInfections = (int) Math.round(maleInfectionsCalc);
                                //System.out.println(maleInfections);
                                return maleInfections;
                            }
                            else break;
                        case Cell.CELL_TYPE_BLANK:
                            System.out.println("Male COVID Infection % Stat Not Found!");

                        default:
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return 9;
        }
    }

     static private int retrieveTotalInfectionsFemale(String country, double totalInfections)
     {
         {
             try {
                 File file = new File("C:\\Users\\smuha\\group11\\data\\CASES_BY_SEX.xlsx"); //creating a new fil
                 FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                 //creating Workbook instance that refers to .xlsx file
                 XSSFWorkbook wb = new XSSFWorkbook(fis);
                 XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
                 Iterator<Row> itr = sheet.iterator();    //iterating over excel file


                 //row of country
                 int countryRow = findRow(sheet, country);

                 //in this particular excel the results we're looking for are always in the 7th column
                 int countryColumn = 6;

                 Cell cell = sheet.getRow(countryRow).getCell(countryColumn);

                 switch (cell.getCellType()) {
                     case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                         //System.out.println("A");
                         String contents = cell.getStringCellValue();
                         if(contents.endsWith("%")){
                             String number = removeLastChar(contents);
                             //System.out.println(number);
                             double d = Double.parseDouble(number);
                             d = d / 100.0;
                             double femaleInfectionsCalc = d * totalInfections;
                             int femaleInfections = (int) Math.round(femaleInfectionsCalc);
                             //System.out.println(maleInfections);
                             return femaleInfections;
                         }
                         else break;
                     case Cell.CELL_TYPE_BLANK:
                         System.out.println("Male COVID Infection % Stat Not Found!");

                     default:
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

         return 9;
     }


    static private int retrieveCountryPopulation(String country)
    {
        {
            try
            {
                File file = new File("C:\\Users\\smuha\\group11\\data\\TOTAL_POPULATION_BOTH_SEXES.xlsx"); //creating a new file instance
                FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                //creating Workbook instance that refers to .xlsx file
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file


                //row of country
                int countryRow = findRow(sheet, country);

                //in this particular excel the results we're looking for are always in the 7th column
                int countryColumn = 7;

                Cell cell = sheet.getRow(countryRow).getCell(countryColumn);

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                        System.out.print("Error: Country Population not found.");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                        double population = (Math.round((cell.getNumericCellValue() * 100.0) *10.0)/10.0);
                        int populationReturn = ((int) Math.round(population)) * 10;
                        return populationReturn;
                    default:
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return 9;
    }

    static private int retrieveCountryPopulationMale(String country){
        {
            try
            {
                File file = new File("C:\\Users\\smuha\\group11\\data\\TOTAL_POPULATION_MALE.xlsx"); //creating a new file instance
                FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                //creating Workbook instance that refers to .xlsx file
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file


                //row of country
                int countryRow = findRow(sheet, country);

                //in this particular excel the results we're looking for are always in the 7th column
                int countryColumn = 7;

                Cell cell = sheet.getRow(countryRow).getCell(countryColumn);

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                        System.out.print("Error: Country Population not found.");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                        double population = (Math.round((cell.getNumericCellValue() * 100.0) *10.0)/10.0);
                        int populationReturn = ((int) Math.round(population)) * 10;
                        return populationReturn;
                    default:
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return 9;
    }

    static private int retrieveCountryPopulationFemale(String country){
        {
            try
            {
                File file = new File("C:\\Users\\smuha\\group11\\data\\TOTAL_POPULATION_FEMALE.xlsx"); //creating a new file instance
                FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                //creating Workbook instance that refers to .xlsx file
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file


                //row of country
                int countryRow = findRow(sheet, country);

                //in this particular excel the results we're looking for are always in the 7th column
                int countryColumn = 7;

                Cell cell = sheet.getRow(countryRow).getCell(countryColumn);

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                        System.out.print("Error: Country Population not found.");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                        double population = (Math.round((cell.getNumericCellValue() * 100.0) *10.0)/10.0);
                        int populationReturn = ((int) Math.round(population)) * 10;
                        return populationReturn;
                    default:
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return 9;
    }

    public static void main(String[] args)
    {

        String country = "Slovenia";
        int totPop = retrieveCountryPopulation(country);
        System.out.println("Country total population: "+totPop);
        int malePop = retrieveCountryPopulationMale(country);
        System.out.println("Country male population: " +malePop);
        int femPop = retrieveCountryPopulationFemale(country);
        System.out.println("Country female population: " +femPop);
        double numberOfInfections = retrieveTotalInfections(country);
        System.out.println("Country Total Infections: "+ intMaker(numberOfInfections));
        int numberOfMaleInfections = retrieveTotalInfectionsMale(country, numberOfInfections);
        System.out.println("Country Male Infections: "+numberOfMaleInfections);
        int numberOfFemaleInfections = retrieveTotalInfectionsFemale(country, numberOfInfections);
        System.out.println("Country Female Infections: "+numberOfFemaleInfections);

    }

    }