import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the aggregated table data for a specific country and timescale.
 * The table aggregates units sold per vendor, computes percentage share,
 * and supports various querying and sorting operations.
 */
public class TableData {
    private List<VendorData> rows;
    private double totalUnits;

    /**
     * Creates a TableData object by filtering and aggregating a list of SalesRecord objects.
     * Only records matching the given country and timescale are included.
     *
     * @param records the list of SalesRecord objects
     * @param country the country to filter by (e.g., "Czech Republic")
     * @param timescale the timescale to filter by (e.g., "2010 Q4")
     */
    public TableData(List<SalesRecord> records, String country, String timescale) {
        // Use a map to aggregate units for each vendor.
        Map<String, VendorData> aggregation = new HashMap<>();
        for (SalesRecord record : records) {
            if (record.getCountry().equalsIgnoreCase(country) &&
                    record.getTimescale().equalsIgnoreCase(timescale)) {
                String vendor = record.getVendor();
                double units = record.getUnits();
                if (aggregation.containsKey(vendor)) {
                    aggregation.get(vendor).addUnits(units);
                } else {
                    aggregation.put(vendor, new VendorData(vendor, units));
                }
                totalUnits += units;
            }
        }
        // Compute share percentages for each vendor.
        rows = new ArrayList<>();
        for (VendorData vendorData : aggregation.values()) {
            double share = (totalUnits > 0) ? (vendorData.getUnits() / totalUnits * 100) : 0;
            vendorData.setShare(share);
            rows.add(vendorData);
        }
    }

    /**
     * Returns the VendorData for the given vendor name.
     *
     * @param vendor the vendor name to search for (case-insensitive)
     * @return the VendorData instance, or null if not found
     */
    public VendorData getVendorData(String vendor) {
        for (VendorData data : rows) {
            if (data.getVendor().equalsIgnoreCase(vendor)) {
                return data;
            }
        }
        return null;
    }

    /**
     * Returns the (zero-based) row index where the given vendor appears.
     *
     * @param vendor the vendor name (case-insensitive)
     * @return the row index, or -1 if the vendor is not found
     */
    public int getRowIndex(String vendor) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getVendor().equalsIgnoreCase(vendor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts the rows alphabetically by vendor name.
     */
    public void sortByVendor() {
        Collections.sort(rows, new Comparator<VendorData>() {
            @Override
            public int compare(VendorData d1, VendorData d2) {
                return d1.getVendor().compareToIgnoreCase(d2.getVendor());
            }
        });
    }

    /**
     * Sorts the rows by the number of units sold.
     */
    public void sortByUnits() {
        Collections.sort(rows, new Comparator<VendorData>() {
            @Override
            public int compare(VendorData d1, VendorData d2) {
                return Double.compare(d1.getUnits(), d2.getUnits());
            }
        });
    }

    /**
     * Returns the list of vendor data rows.
     *
     * @return a List of VendorData
     */
    public List<VendorData> getRows() {
        return rows;
    }

    /**
     * Returns the total units (the sum of units for all vendors).
     *
     * @return total units sold for the filtered data.
     */
    public double getTotalUnits() {
        return totalUnits;
    }
}
