import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CountryExportsCSV {
    private String countryInfo(CSVParser parser, String country) {
        for(CSVRecord rec : parser) {
            String c = rec.get("Country");
            if(c.equalsIgnoreCase(country)) {
                return c+": "+rec.get("Exports")+": "+rec.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    private void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord rec : parser) {
            String c = rec.get("Exports");
            if(c.contains(exportItem1) && c.contains(exportItem2)) {
                System.out.println(rec.get("Country"));
            }
        }
    }

    private int numberOfExporters(CSVParser parser, String exportItem) {
        int c = 0;
        for(CSVRecord rec : parser) {
            if(rec.get("Exports").contains(exportItem)) c++;
        }
        return c;
    }

    private void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord rec : parser) {
            if(rec.get("Value (dollars)").length() > amount.length()) {
                System.out.println(rec.get("Country") + " " + rec.get("Value (dollars)"));
            }
        }
    }

    private void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        CountryExportsCSV ob = new CountryExportsCSV();
        ob.tester();
    }
}
