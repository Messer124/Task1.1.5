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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        HttpSession session = req.getSession();

        String role = "";
        try {
            role = session.getAttribute("role").toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (role.equals("")) {
            response.sendRedirect("/logout");
        }

        User user = UserService.getInstance().selectUserByLogin(req.getSession().getAttribute("login").toString());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("userPage.jsp").forward(req, response);
    }
}
