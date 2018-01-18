package command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@WebServlet("/web/weather.jsp")
public class Weather implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String text = "some text";
        String zip = request.getParameter("zip");
        URLConnection connection = null;
        PrintWriter pw = null;
        try {
            URL url = new URL("http://api.wunderground.com/api/9f190758a045e35c/geolookup/conditions/q/" + zip + ".json");
            connection = url.openConnection();
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (connection != null && pw != null) {
            pw.write("<br> " + zip);
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            try {
                response.getWriter().write(text);       // Write response body.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}
