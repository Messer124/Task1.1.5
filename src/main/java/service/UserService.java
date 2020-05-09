package service;

import daoFactory.UserDaoFactory;
import interfaceDao.UserDAO;
import model.User;
import org.hibernate.Query;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class UserService{
    private static UserService userService;
    private UserDAO userDAO;

    private  UserService() {
        try {
            userDAO = new UserDaoFactory().createDAO();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void insertUser(HttpServletRequest req) {
        if (!req.getServletPath().equals("/addUser")) {
            System.out.println("wrong url for the action");
            return;
        }

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

        userDAO.insertUser(new User(role, name, email, country, login, password));
    }

    public boolean isUserExist(String login) {
        if (login.isEmpty()) return false;
        return userDAO.isUserExist(login);
    }

    public User selectUserByLogin(String login) {
        return userDAO.selectUserByLogin(login);
    }

    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    public void deleteUser(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        userDAO.deleteUser(id);
    }

    public void updateUser(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        String role = req.getParameter("role");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        User current = userDAO.selectUserById(id);

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

        userDAO.updateUser(new User(id, role, name, email, country, login, password));
    }
}
