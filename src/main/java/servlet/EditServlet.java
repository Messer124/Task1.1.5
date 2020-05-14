package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static Long id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Long.parseLong(req.getParameter("id"));
        req.getRequestDispatcher("editPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = UserService.getInstance().selectUserById(id);

        String role = req.getParameter("role");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (role.isEmpty())  {
            role = current.getRole();
        } else {
            if (!role.matches("user|admin")) return;
        }

        if (name.isEmpty()) name = current.getName();
        if (email.isEmpty()) email = current.getEmail();
        if (country.isEmpty()) country = current.getCountry();
        if (login.isEmpty()) login = current.getLogin();
        if (password.isEmpty()) password = current.getPassword();

        UserService.getInstance().updateUser(new User(id, role, name, email, country, login, password));
        resp.sendRedirect("/admin");
    }
}
