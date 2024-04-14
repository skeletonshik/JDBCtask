package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(
                "CREATE TABLE IF NOT EXISTS typeone.USERS (" +
                        "  `ID` BIGINT(200) NOT NULL AUTO_INCREMENT," +
                        "  `NAME` VARCHAR(50) NULL," +
                        "  `LASTNAME` VARCHAR(50) NULL," +
                        "  `AGE` TINYINT(100) NULL," +
                        "  PRIMARY KEY (`ID`));"
        ).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS typeone.users").executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        User user = new User(name, lastName, age);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = session.createQuery("from User").getResultList();
        transaction.commit();
        session.close();
        return users;
    }


    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("truncate table typeone.Users").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void closeConnection() {
        sessionFactory.close();
    }
}
