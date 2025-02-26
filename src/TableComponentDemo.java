import java.io.IOException;
import java.util.List;

/**
 * Demonstrates the usage of the table component.
 * Loads the CSV data, filters for a specific country and timescale,
 * aggregates the vendor data, and performs queries, sorting, and HTML export.
 */
public class TableComponentDemo {
    public static void main(String[] args) {
        SalesDataLoader loader = new SalesDataLoader();
        List<SalesRecord> records;
        try {
            // Load the CSV file (ensure "data.csv" is in the correct location)
            records = loader.loadRecords("data/data.csv");
        } catch (IOException e) {
            System.err.println("Error loading CSV: " + e.getMessage());
            return;
        }

        // Create aggregated table data for "Czech Republic" and "2010 Q4"
        TableData tableData = new TableData(records, "Czech Republic", "2010 Q4");

        // Example query: Retrieve data for vendor "Dell"
        VendorData dellData = tableData.getVendorData("Dell");
        if (dellData != null) {
            System.out.println("Dell sold " + String.format("%.2f", dellData.getUnits()) +
                    " units (" + String.format("%.2f", dellData.getShare()) + "% share).");
        } else {
            System.out.println("Vendor 'Dell' not found.");
        }

        // Determine which row (1-based index) contains information about Dell.
        int dellRowIndex = tableData.getRowIndex("Dell");
        if (dellRowIndex >= 0) {
            System.out.println("Dell is in row " + (dellRowIndex + 1) + ".");
        }

        // Sort rows alphabetically by vendor name
        tableData.sortByVendor();
        // (Alternatively, to sort by units, use: tableData.sortByUnits();)

        // Export to HTML and print the output
        HTMLExporter htmlExporter = new HTMLExporter();
        String htmlOutput = htmlExporter.export(tableData);
        System.out.println("\nExported HTML Table:\n" + htmlOutput);

        // Outlined exporters for Excel and CSV
        ExcelExporter excelExporter = new ExcelExporter();
        CSVExporter csvExporter = new CSVExporter();
        System.out.println("\nExcel Export (outline): " + excelExporter.export(tableData));
        System.out.println("CSV Export (outline): " + csvExporter.export(tableData));
    }
}
