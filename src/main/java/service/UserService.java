package service;

import daoFactory.UserDaoFactory;
import dao.interfaceDao.UserDAO;
import model.User;

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

    public void insertUser(User user) {
        userDAO.insertUser(user);
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

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public User selectUserById(Long id) {
        return userDAO.selectUserById(id);
    }
}
