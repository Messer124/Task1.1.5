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


@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/loginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        UserService service = UserService.getInstance();
        String login = req.getParameter("login");
        boolean isUserExist = service.isUserExist(login);

        if (isUserExist) {
            User user = service.selectUserByLogin(login);
            if (!user.getPassword().equals(req.getParameter("password"))) {
                req.getRequestDispatcher("pages/accessError.jsp").forward(req, resp);
            } else {
                session.setAttribute("role", user.getRole());
                session.setAttribute("password", req.getParameter("password"));
                session.setAttribute("login", req.getParameter("login"));

                if (user.getRole().equals("admin")) resp.sendRedirect("/admin");
                else resp.sendRedirect("/user");
            }
        }

    }
}
