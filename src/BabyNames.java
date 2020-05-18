import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {
    private void totalBirths() {
        FileResource fr = new FileResource();
        int total = 0, boys = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals("M")) {
                boys ++;
            }
            total ++;
        }
        System.out.println("Total Births : " + total + "\nTotal Boys : " + boys + "\nTotal Girls : " + (total-boys));
    }

    private int getRank(int year, String name, String gender) {
        int rank = 0;
        boolean found = false;
        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_by_year/yob"+year+".csv").getCSVParser(false)) {
//        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_test/yob"+year+"short.csv").getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                rank++;
                if(rec.get(0).equalsIgnoreCase(name)) {
                    found = true;
                    break;
                }
            }
        }
        if(found) return rank;
        else return -1;
    }

    private String getName(int year, int rank, String gender) {
        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_by_year/yob"+year+".csv").getCSVParser(false)) {
//        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_test/yob"+year+"short.csv").getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                rank--;
                if(rank == 0) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    private void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if " + (gender.equals("M")?"he":"she") +" was born in " + newYear + ".");
    }

    private int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int maxRank = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(f.toString().substring(f.toString().lastIndexOf("/")+4, f.toString().lastIndexOf("/")+8));
            int n = getRank(year, name, gender);
            if(n < maxRank && n != -1) {
                maxRank = n;
                maxYear = year;
            }
        }
        if(maxRank == Integer.MAX_VALUE) return -1;
        return maxYear;
    }

    private double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double avg = 0;
        double count = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(f.toString().substring(f.toString().lastIndexOf("/") + 4, f.toString().lastIndexOf("/") + 8));
            int n = getRank(year, name, gender);
            if (n != -1) {
                count++;
                avg += n;
            }
        }
        if(avg == 0) return -1;
        return avg/count;
    }

    private int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int rank = getRank(year, name, gender);
        int currRank = 1;
        int total = 0;
        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_by_year/yob"+year+".csv").getCSVParser(false)) {
//        for(CSVRecord rec : new FileResource("/home/anmol/JavaProjects/Coursera/JavaProgramming/data/us_babynames/us_babynames_test/yob"+year+"short.csv").getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                currRank++;
                total += Integer.parseInt(rec.get(2));
                if(currRank == rank) break;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        BabyNames ob = new BabyNames();
        ob.totalBirths();
//        System.out.println(ob.getRank(1971, "Frank", "M"));
//        System.out.println(ob.getName(1982, 450, "M"));
//        ob.whatIsNameInYear("Susan", 1972, 2014, "F");
//        System.out.println(ob.yearOfHighestRank("Mich", "M"));
//        System.out.println(ob.getAverageRank("Robert", "M"));
//        System.out.println(ob.getTotalBirthsRankedHigher(1990,"Drew", "M"));
    }
}
