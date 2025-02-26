/**
 * An interface for exporting TableData to various formats.
 */
public interface Exporter {
    /**
     * Exports the provided table data to a string in the desired format.
     *
     * @param tableData the table data to export
     * @return the exported data as a String
     */
    String export(TableData tableData);
}
