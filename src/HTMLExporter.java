/**
 * Exports TableData to an HTML table.
 * The output format follows the required example:
 * Table 1, PC Quarterly Market Share, the Czech Republic, 4Q10.
 */
public class HTMLExporter implements Exporter {

    /**
     * Exports the table data to HTML format.
     *
     * @param tableData the table data to export
     * @return an HTML string representing the table
     */
    @Override
    public String export(TableData tableData) {
        StringBuilder html = new StringBuilder();
        html.append("<table border='1'>\n");
        html.append("<caption>PC Quarterly Market Share, the Czech Republic, 4Q10</caption>\n");
        html.append("<tr><th>Vendor</th><th>Units</th><th>Share</th></tr>\n");
        for (VendorData row : tableData.getRows()) {
            html.append("<tr>");
            html.append("<td>").append(row.getVendor()).append("</td>");
            html.append("<td>").append(String.format("%.2f", row.getUnits())).append("</td>");
            html.append("<td>").append(String.format("%.2f", row.getShare())).append("%</td>");
            html.append("</tr>\n");
        }
        // Add a total row.
        html.append("<tr>");
        html.append("<td><strong>Total</strong></td>");
        html.append("<td><strong>").append(String.format("%.2f", tableData.getTotalUnits())).append("</strong></td>");
        html.append("<td><strong>100%</strong></td>");
        html.append("</tr>\n");
        html.append("</table>");
        return html.toString();
    }
}
