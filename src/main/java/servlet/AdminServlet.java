package servlet;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires", "0");

        showAdminPage(req, resp);

    }

    protected void showAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.getInstance().selectAllUsers();
        req.setAttribute("userList", users);
        req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
    }
}
