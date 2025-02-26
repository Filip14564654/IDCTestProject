import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

/**
 * Loads raw sales records from a CSV file.
 * Assumes the first row is a header with the following columns:
 * Country, Timescale, Vendor, Units.
 */
public class SalesDataLoader {

    /**
     * Loads the CSV file and returns a List of SalesRecord objects.
     *
     * @param filePath the path to the CSV file (e.g., "data.csv")
     * @return a List of SalesRecord objects
     * @throws IOException if there is an error reading the file
     */
    public List<SalesRecord> loadRecords(String filePath) throws IOException {
        List<SalesRecord> records = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] line;
        boolean firstLine = true;
        while ((line = reader.readNext()) != null) {
            // Skip header row
            if (firstLine) {
                firstLine = false;
                continue;
            }
            if (line.length < 4) continue;
            try {
                String country = line[0].trim();
                String timescale = line[1].trim();
                String vendor = line[2].trim();
                // The units might be a floating point number
                double units = Double.parseDouble(line[3].trim());
                records.add(new SalesRecord(country, timescale, vendor, units));
            } catch (Exception e) {
                // Skip rows that cannot be parsed correctly.
            }
        }
        reader.close();
        return records;
    }
}
