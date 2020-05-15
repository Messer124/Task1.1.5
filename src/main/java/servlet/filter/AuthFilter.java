package servlet.filter;

import model.User;
import service.UserService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


@WebFilter(urlPatterns = {"/admin", "/user", "/login"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
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
                    }
                }
                break;
            case "/admin":
                String role = session.getAttribute("role").toString();
                if (!role.equals("admin")) {
                    resp.sendRedirect("accessError.jsp");
                    return;
                }
                break;
            case "/user":
                String newRole = session.getAttribute("role").toString();
                if (!newRole.matches("user|admin")) {
                    resp.sendRedirect("accessError.jsp");
                }
                break;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
