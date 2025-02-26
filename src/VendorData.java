/**
 * Represents aggregated data for a vendor.
 * Contains the vendor name, total units sold, and the computed percentage share.
 */
public class VendorData {
    private String vendor;
    private double units;
    private double share; // percentage share (e.g. 10.6 means 10.6%)

    public VendorData(String vendor, double units) {
        this.vendor = vendor;
        this.units = units;
    }

    public String getVendor() {
        return vendor;
    }

    public double getUnits() {
        return units;
    }

    public void addUnits(double units) {
        this.units += units;
    }

    public double getShare() {
        return share;
    }

    public void setShare(double share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return vendor + "\t" + String.format("%.2f", units) + "\t" + String.format("%.2f", share) + "%";
    }
}
