/**
 * Represents one record from the CSV file.
 * Each SalesRecord holds the country, timescale, vendor name, and units sold.
 */
public class SalesRecord {
    private String country;
    private String timescale;
    private String vendor;
    private double units;

    public SalesRecord(String country, String timescale, String vendor, double units) {
        this.country = country;
        this.timescale = timescale;
        this.vendor = vendor;
        this.units = units;
    }

    public String getCountry() {
        return country;
    }

    public String getTimescale() {
        return timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public double getUnits() {
        return units;
    }
}
