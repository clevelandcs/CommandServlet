package command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CmdServlet", displayName = "CmdServlet", urlPatterns = {"/CmdServlet"}, loadOnStartup = 1)
public class CmdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Command> commands;
    private String jspdir = "/";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initCommands();
    }

    private void initCommands() {
        commands = new HashMap<>();
        commands.put("weather", new Weather());
        commands.put("locator", new Locator());
    }

    private Command lookupCommand(String cmd) {
        return commands.get(cmd);
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String next = null;
        try {
            Command cmd = lookupCommand(request.getParameter("cmd"));
            cmd.execute(request, response);
            next = cmd.getNext();
            System.out.println("CmdServlet: cmd = " + cmd + ", next = " + cmd.getNext());
        } catch (CommandException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher(jspdir + next);
        rd.forward(request, response);
    }
}
