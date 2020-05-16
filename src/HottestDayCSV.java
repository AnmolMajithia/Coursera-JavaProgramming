import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class HottestDayCSV {
    private CSVRecord getLargestOfTwo(CSVRecord largestSoFar, CSVRecord curr) {
        if(largestSoFar == null) {
            largestSoFar = curr;
        }
        else {
            double currTemp = Double.parseDouble(curr.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currTemp > largestTemp) {
                largestSoFar = curr;
            }
        }
        return largestSoFar;
    }

    private CSVRecord highestInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for(CSVRecord curr : parser) {
            largestSoFar = getLargestOfTwo(largestSoFar, curr);
        }
        return largestSoFar;
    }

    private CSVRecord hottestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curr = highestInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(largestSoFar, curr);
        }
        return largestSoFar;
    }

    public static void main(String[] args) {
        HottestDayCSV ob = new HottestDayCSV();
        CSVRecord hottest = ob.hottestInManyDays();
        System.out.println("Hottest Temperature: " + hottest.get("TemperatureF") + " at " + hottest.get("DateUTC"));
    }
}
