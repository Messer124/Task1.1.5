package dao.interfaceDao;

import model.User;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user);
    List<User> selectAllUsers();
    boolean deleteUser(Long id);
    boolean updateUser(User user);
    User selectUserById(Long id);
    User selectUserByLogin(String login);
    boolean isUserExist(String login);
}
