package servlet;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires", "0");

        String role = req.getSession().getAttribute("role").toString();
        if (role.matches("admin|user")) showUserPage(req, resp);
        else resp.sendRedirect("/logout");
    }

    protected void showUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.getInstance().selectUserByLogin(req.getSession().getAttribute("login").toString());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("userPage.jsp").forward(req, resp);
    }
}
