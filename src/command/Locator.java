package command;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Locator implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String address = request.getParameter("address");
        try {
            URL googleWeatherXml = new URL("http://freegeoip.net/xml/" + address);

            URLConnection uc = googleWeatherXml.openConnection();
            uc.connect();
            PrintWriter pw = response.getWriter();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(uc.getInputStream());
            doc.getDocumentElement().normalize();

            String[] labels = {"IP address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude"};
            String[] keys = {"IP", "City", "RegionName", "ZipCode", "CountryName", "Latitude", "Longitude"};
            pw.println("<html>");
            pw.println("<head><title>Internet Geolocation</title></title>");
            pw.println("<body>");
            pw.println("<table bo>");

            for (int i = 0; i < labels.length; i++) {
                pw.println("<tr>");
                pw.println("<td>" + labels[i] + "</td>");
                NodeList label = doc.getElementsByTagName(keys[i]);
                Element value = (Element) label.item(0);
                pw.println("<td>" + value.getTextContent() + "</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
            pw.println("</body></html>");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
