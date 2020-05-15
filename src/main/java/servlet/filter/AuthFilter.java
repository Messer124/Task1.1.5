package servlet.filter;

import model.User;
import service.UserService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/admin", "/user", "/login"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String path = req.getServletPath();

        switch (path) {
            case "/login":
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
                break;
            case "/admin":
                String role = session.getAttribute("role").toString();
                if (!role.equals("admin")) {
                    session.setAttribute("role", "forbidden");
                }
                break;
            case "/user":
                String newRole = session.getAttribute("role").toString();
                if (!newRole.matches("user|admin")) {
                    session.setAttribute("role", "forbidden");
                }
                break;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
