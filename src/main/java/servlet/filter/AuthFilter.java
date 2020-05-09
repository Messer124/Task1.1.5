package servlet.filter;
import daoFactory.UserDaoFactory;
import interfaceDao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/login")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDAO dao = new UserDaoFactory().createDAO();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isUserExist = dao.isUserExist(login);

        HttpSession session = req.getSession();

        if (nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            String role = session.getAttribute("role").toString();
            moveToMenu(req, resp, role);
        } else if (isUserExist) {
            User user = dao.selectUserByLogin(login);
            String role = user.getRole();

            while (!user.getPassword().equals(password)) {
                req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
            }

            session.setAttribute("password", password);
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            moveToMenu(req, resp, role);
        } else {
            req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
        }
        //chain.doFilter(request, response);
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, String role) throws ServletException, IOException {

        if (role.equals("admin")) {
            req.getRequestDispatcher("/admin").forward(req, resp);
        } else if (role.equals("user")) {
            req.getRequestDispatcher("/user").forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
