package daoFactory;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import interfaceDao.UserDAO;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    FileInputStream fis = new FileInputStream("C:/Users/messe/Downloads/JavaLearning/Task1.1.2/src/main/resources/config.properties");
    static Properties properties = new Properties();

    public UserDaoFactory() throws FileNotFoundException {
    }

    public UserDAO createDAO() throws IOException {
        properties.load(fis);
        String type = properties.getProperty("daotype");
        if (type.equalsIgnoreCase("UserHibernateDAO")) {
            return new UserHibernateDAO(DBHelper.getInstance().getSessionFactory().openSession());
        } else if (type.equalsIgnoreCase("UserJdbcDAO")) {
            return new UserJdbcDAO();
        } else {
            throw new RuntimeException(type + " is not supported!");
        }
    }
}
