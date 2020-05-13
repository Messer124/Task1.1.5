package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUser")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String role = req.getParameter("role");
        if (!role.matches("user|admin") || role.isEmpty()) return;
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (name.isEmpty() || email.isEmpty() || country.isEmpty() || login.isEmpty() || password.isEmpty()) {
            return;
        }

        UserService.getInstance().insertUser(new User(role, name, email, country, login, password));
        resp.sendRedirect("loginPage.jsp");
    }
}
