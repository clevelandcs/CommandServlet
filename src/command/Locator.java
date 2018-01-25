package command;

import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Locator implements Command {
    private String next = "locator.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String address = request.getParameter("address");
        try {
            URL googleWeatherXml = new URL("http://freegeoip.net/xml/" + address);

            URLConnection uc = googleWeatherXml.openConnection();
            uc.connect();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(uc.getInputStream());
            doc.getDocumentElement().normalize();
            HashMap<String, String> data = new HashMap<>(7);
            String[] labels = {"IP address", "City", "State", "Zip Code", "Country", "Latitude", "Longitude"};
            String[] keys = {"IP", "City", "RegionName", "ZipCode", "CountryName", "Latitude", "Longitude"};

            for (int i = 0; i < labels.length; i++) {
                data.put(labels[i], doc.getElementsByTagName(keys[i]).item(0).getTextContent());
            }

            request.setAttribute("data", data);
            request.setAttribute("labels",labels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNext() {
        return next;
    }
}
