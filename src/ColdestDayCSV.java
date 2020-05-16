import org.apache.commons.csv.*;
import edu.duke.*;

import java.io.File;

public class ColdestDayCSV {
    private CSVRecord coldestInTwo(CSVRecord coldestSoFar, CSVRecord curr) {
        if(coldestSoFar == null) {
            if(curr.get("TemperatureF").equals("-9999")) return null;
            return curr;
        }
        double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
        double currTemp = Double.parseDouble(curr.get("TemperatureF"));
        if(currTemp < coldestTemp && currTemp != -9999) {
            return curr;
        }
        return coldestSoFar;
    }

    private CSVRecord lowestInTwo(CSVRecord lowestSoFar, CSVRecord curr) {
        if(lowestSoFar == null) {
            if(curr.get("Humidity").equals("N/A")) return null;
            return curr;
        }
        if(curr.get("Humidity").equals("N/A")) return lowestSoFar;
        double lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
        double currHumi = Double.parseDouble(curr.get("Humidity"));
        if(currHumi < lowestHumi) {
            return curr;
        }
        return lowestSoFar;
    }

    private CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for(CSVRecord curr : parser) {
            coldestSoFar = coldestInTwo(coldestSoFar, curr);
        }
        return coldestSoFar;
    }

    private String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        File coldest = null;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curr = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = coldestInTwo(coldestSoFar, curr);
            if (coldestSoFar == null) continue;
            if (coldestSoFar.get("DateUTC").equals(curr.get("DateUTC"))) {
                coldest = f;
            }
        }
        if (coldest == null) return "not found";
        return coldest.toString();
    }

    private CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for(CSVRecord curr : parser) {
            lowestSoFar = lowestInTwo(lowestSoFar, curr);
        }
        return lowestSoFar;
    }

    private CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curr = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = lowestInTwo(lowestSoFar, curr);
        }
        return lowestSoFar;
    }

    private double averageTemperatureInFile(CSVParser parser) {
        double tot = 0;
        double count = 0;
        for (CSVRecord rec : parser) {
            count++;
            tot += Double.parseDouble(rec.get("TemperatureF"));
        }
        return tot/count;
    }

    private double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double avg = 0;
        double tot = 0;
        for(CSVRecord rec : parser) {
            if(Integer.parseInt(rec.get("Humidity")) >= value) {
                tot += Double.parseDouble(rec.get("TemperatureF"));
                avg++;
            }
        }
        if (tot == 0) return 0;
        return tot/avg;
    }

    private void testAverageTemperatureWithHighHumidityInFile() {
        double res = averageTemperatureWithHighHumidityInFile(new FileResource().getCSVParser(), 80);
        if(res == 0) System.out.println("No temperatures with that humidity");
        else System.out.println("Avg temp with that humidity : " + res);
    }

    private void testAverageTemperatureInFile() {
        System.out.println("Average temperature in file is " + averageTemperatureInFile(new FileResource().getCSVParser()));
    }

    private void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    private void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    private void testFileWithColdestTemperature() {
        String coldest = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldest);
        CSVParser parser = fr.getCSVParser();
        System.out.println("Coldest day was in file " + coldest.substring(coldest.lastIndexOf("/")+1));
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(parser).get("TemperatureF"));
        parser = fr.getCSVParser();
        System.out.println("All the temperatures on the coldest day were:");
        for(CSVRecord rec : parser) {
            System.out.println(rec.get("DateUTC") + ": " + rec.get("TemperatureF"));
        }
    }

    private void testColdestHourInFile() {
        CSVParser parser = new FileResource().getCSVParser();
        CSVRecord rec = coldestHourInFile(parser);
        System.out.println("Coldest Hour in file : " + rec.get("DateUTC") + " with a temperature of : " + rec.get("TemperatureF"));
    }

    public static void main(String[] args) {
        ColdestDayCSV ob = new ColdestDayCSV();
//        ob.testColdestHourInFile();
        ob.testFileWithColdestTemperature();
//        ob.testLowestHumidityInFile();
//        ob.testLowestHumidityInManyFiles();
//        ob.testAverageTemperatureInFile();
//        ob.testAverageTemperatureWithHighHumidityInFile();
    }
}
