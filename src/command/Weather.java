package command;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

import java.net.URL;
import java.net.URLConnection;

@WebServlet("/web/weather.jsp")
/**
 * Simple weather servlet command generating dynamic HTML table from wunderground XML
 *
 * XML parsing of wunderground API adapted from: http://usmanali112.blogspot.com/2012/07/java-weather-underground-api.html
 */
public class Weather implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String zip = request.getParameter("zip");
        try {
            URL googleWeatherXml = new URL("http://api.wunderground.com/api/9f190758a045e35c/forecast/q/" + zip + ".xml");

            URLConnection uc = googleWeatherXml.openConnection();
            uc.connect();
            PrintWriter pw = response.getWriter();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(uc.getInputStream());
            doc.getDocumentElement().normalize();
            pw.println("<html>");
            pw.println("<head><title>Weather Forecast</title></title>");
            pw.println("<body>");
            pw.println("<table>");
            NodeList forecast = doc.getElementsByTagName("forecast");
            for (int a = 0; a < forecast.getLength(); a++) {
                Node forecastNode = forecast.item(a);

                if (forecastNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element forecastElement = (Element) forecastNode;

                    NodeList simpleforecast = forecastElement.getElementsByTagName("simpleforecast");

                    for (int b = 0; b < simpleforecast.getLength(); b++) {
                        Node simpleforecastNode = simpleforecast.item(b);

                        if (simpleforecastNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element simpleforecastElement = (Element) simpleforecastNode;

                            NodeList forecastdays = simpleforecastElement.getElementsByTagName("forecastdays");

                            for (int c = 0; c < forecastdays.getLength(); c++) {
                                Node forecastdaysNode = forecastdays.item(c);

                                if (forecastdaysNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element forecastdaysElement = (Element) forecastdaysNode;

                                    NodeList forecastday = forecastdaysElement.getElementsByTagName("forecastday");

                                    for (int d = 0; d < forecastday.getLength(); d++) {
                                        Node forecastdayNode = forecastday.item(d);

                                        if (forecastdayNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element forecastdayElement = (Element) forecastdayNode;

                                            NodeList date = forecastdayElement.getElementsByTagName("date");

                                            for (int e = 0; e < date.getLength(); e++) {
                                                Node dateNode = date.item(e);

                                                if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element dateElement = (Element) dateNode;

                                                    NodeList weekday = dateElement.getElementsByTagName("weekday");
                                                    Element day = (Element) weekday.item(0);
                                                    pw.println("<tr>");
                                                    pw.println("<td>Day of week:</td>");
                                                    pw.println("<td>" + day.getTextContent() + "</td>");
                                                    pw.println("</tr>");
                                                }
                                            }

                                            NodeList high = forecastdayElement.getElementsByTagName("high");

                                            for (int f = 0; f < high.getLength(); f++) {
                                                Node highNode = high.item(f);

                                                if (highNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element highElement = (Element) highNode;
                                                    NodeList celsius = highElement.getElementsByTagName("celsius");
                                                    Element cel = (Element) celsius.item(0);
                                                    pw.println("<tr>");
                                                    pw.println("<td>High cel:</td>");
                                                    pw.println("<td>" + cel.getTextContent() + "</td>");
                                                    pw.println("</tr>");
                                                }
                                            }

                                            NodeList low = forecastdayElement.getElementsByTagName("low");

                                            for (int g = 0; g < low.getLength(); g++) {
                                                Node lowNode = low.item(g);

                                                if (lowNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lowElement = (Element) lowNode;
                                                    NodeList celsius = lowElement.getElementsByTagName("celsius");
                                                    Element cel = (Element) celsius.item(0);
                                                    pw.println("<tr>");
                                                    pw.println("<td>Low cel:</td>");
                                                    pw.println("<td>" + cel.getTextContent() + "</td>");
                                                    pw.println("</tr>");
                                                }
                                            }

                                            NodeList avewind = forecastdayElement.getElementsByTagName("avewind");

                                            for (int h = 0; h < avewind.getLength(); h++) {
                                                Node avewindNode = avewind.item(h);

                                                if (avewindNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element avewindElement = (Element) avewindNode;
                                                    NodeList mph = avewindElement.getElementsByTagName("mph");
                                                    Element mp = (Element) mph.item(0);
                                                    pw.println("<tr>");
                                                    pw.println("<td>mph:</td>");
                                                    pw.println("<td>" + mp.getTextContent() + "</td>");
                                                    pw.println("</tr>");

                                                    NodeList dir = avewindElement.getElementsByTagName("dir");
                                                    Element dr = (Element) dir.item(0);
                                                    pw.println("<tr>");
                                                    pw.println("<td>Dir:</td>");
                                                    pw.println("<td>" + dr.getTextContent() + "</td>");
                                                    pw.println("</tr>");
                                                }
                                            }

                                            NodeList conditions = forecastdayElement.getElementsByTagName("conditions");
                                            Element con = (Element) conditions.item(0);
                                            pw.println("<tr>");
                                            pw.println("<td>conditions:</td>");
                                            pw.println("<td>" + con.getTextContent() + "</td>");
                                            pw.println("</tr>");

                                            NodeList avehumidity = forecastdayElement.getElementsByTagName("avehumidity");
                                            Element ave = (Element) avehumidity.item(0);
                                            pw.println("<tr>");
                                            pw.println("<td>average humidity:</td>");
                                            pw.println("<td>" + ave.getTextContent() + "</td>");
                                            pw.println("</tr>");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pw.println("</body></html>");
            pw.close();
        } catch (Exception ex) {
            System.out.println((ex.getMessage()));
        }
        return null;
    }
}
