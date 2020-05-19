package servlet.filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user", "/admin"})
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        String role;

        switch (path) {
            case "/admin":
                role = session.getAttribute("role").toString();
                if (!role.equals("admin")) {
                    resp.sendRedirect("WEB-INF/pages/accessError.jsp");
                    return;
                }
                break;
            case "/user":
                role = session.getAttribute("role").toString();
                if (!role.matches("user|admin")) {
                    resp.sendRedirect("WEB-INF/pages/accessError.jsp");
                }
                break;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
