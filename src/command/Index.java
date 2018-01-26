package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements Command {
    private String next = "index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.setAttribute("menu", request.getParameter("menu"));
        return null;
    }

    @Override
    public String getNext() {
        return next;
    }
}
