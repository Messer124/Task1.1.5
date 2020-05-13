package daoFactory;

import dao.Implementation.UserHibernateDAO;
import dao.Implementation.UserJdbcDAO;
import dao.interfaceDao.UserDAO;
import util.DBHelper;
import util.PropertyReader;
import java.io.IOException;


public class UserDaoFactory {
    public UserDAO createDAO() throws IOException {
        PropertyReader reader = new PropertyReader();
        String type = reader.read("daotype");

        if (type.equalsIgnoreCase("UserHibernateDAO")) {
            return new UserHibernateDAO(DBHelper.getInstance().getSessionFactory().openSession());
        } else if (type.equalsIgnoreCase("UserJdbcDAO")) {
            return new UserJdbcDAO();
        } else {
            return new UserJdbcDAO();
        }
    }
}
