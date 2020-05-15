package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;

@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = session.getAttribute("role").toString();

        if (role.equals("forbidden")) {
            resp.sendRedirect("accessError.jsp");

        } else if (role.matches("admin|user")) {
            session.setAttribute("password", req.getParameter("password"));
            session.setAttribute("login", req.getParameter("login"));

            if (role.equals("admin")) resp.sendRedirect("/admin");
            else resp.sendRedirect("/user");
        }
    }
}
