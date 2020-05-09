package dao;

import interfaceDao.UserDAO;
import model.User;
import util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO users (role, name, email, country, login, password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String IS_USER_EXIST = "SELECT * FROM users WHERE login = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET role = ?, name = ?, email = ?, country = ?, login = ?, password = ? WHERE id = ?";

    public boolean insertUser(User user) {
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getRole());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }
    public User selectUserById(Long id) {
        User user = new User();
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            user.setId(id);
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setCountry(resultSet.getString("country"));
            return user;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public User selectUserByLogin(String login) {
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setRole(resultSet.getString("role"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setCountry(resultSet.getString("country"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public boolean isUserExist(String login) {
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(IS_USER_EXIST)) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setRole(resultSet.getString("role"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setCountry(resultSet.getString("country"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return users;
    }

    public boolean deleteUser(Long id) {
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }

    public boolean updateUser(User user) {
        try (Connection connection = DBHelper.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getRole());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());
            statement.setLong(7, user.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }
}
