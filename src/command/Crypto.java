package command;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Crypto implements Command {
    private String next = "crypto.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String currencies = request.getParameter("currencies");
        try {
            URL cryptoCompare = new URL("https://min-api.cryptocompare.com/data/pricemulti?fsyms=" + currencies +  "&tsyms=BTC,USD");
            String jsonString = readUrl(cryptoCompare);
            System.out.println(jsonString);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse(jsonString);
            HashMap data = new HashMap(jsonObject.size());
            for (Object e: jsonObject.entrySet()) {
                Map.Entry<?, ?> entry = ((Map.Entry<?, ?>) e);
                data.put(entry.getKey().toString(), entry.getValue().toString());
            }
            System.out.println(data.toString());
            //for (int i = 0; i < labels.length; i++) {
            //    data.put(labels[i], doc.getElementsByTagName(keys[i]).item(0).getTextContent());
            //}

            request.setAttribute("data", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getNext() {
        return next;
    }

    private static String readUrl(URL url) throws Exception {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
