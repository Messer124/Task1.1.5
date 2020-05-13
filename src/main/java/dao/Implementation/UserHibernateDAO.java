package dao.Implementation;

import dao.interfaceDao.UserDAO;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean insertUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        return true;
    }

    @Override
    public User selectUserByLogin(String login) {
        Query query = session.createQuery("from User where login = :loginParam");
        query.setParameter("loginParam", login);
        return (User)query.list().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> selectAllUsers() {
        session.clear();
        List<User> allUsers =  session.createQuery("FROM User").list();
        return allUsers;
    }

    public User selectUserById(Long id) {
        return (User) session.get(User.class, id);
    }

    public boolean isUserExist(String login) {
        if (login == null) return false;
        Query query = session.createQuery("from User where login = :loginParam").setString("loginParam", login);
        return query.list().size()>0;
    }

    @Override
    public boolean deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User where id = :idParam");
        query.setParameter("idParam", id);
        boolean isDeleted = query.executeUpdate() > 0;
        transaction.commit();
        return isDeleted;
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set role = :roleParam, name = :nameParam, email = :emailParam," +
                " country = :countryParam, login = :loginParam, password = :passParam where id = :idParam");
        query.setParameter("roleParam", user.getRole());
        query.setParameter("nameParam", user.getName());
        query.setParameter("emailParam", user.getEmail());
        query.setParameter("countryParam", user.getCountry());
        query.setParameter("loginParam", user.getLogin());
        query.setParameter("passParam", user.getPassword());
        query.setParameter("idParam", user.getId());

        boolean isUpdated = query.executeUpdate() > 0;
        transaction.commit();
        return isUpdated;
    }
}
