package servlet.filter;

import daoFactory.UserDaoFactory;
import dao.interfaceDao.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/login")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String login = req.getParameter("login");

        if (login == null) {
            session.setAttribute("role", "forbidden");
        } else {
            boolean isUserExist = UserService.getInstance().isUserExist(login);

            if (isUserExist) {
                User user = UserService.getInstance().selectUserByLogin(login);
                if (!user.getPassword().equals(req.getParameter("password"))) {
                    session.setAttribute("role", "forbidden");
                } else {
                    session.setAttribute("role", user.getRole());
                }
            } else session.setAttribute("role", "forbidden");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
