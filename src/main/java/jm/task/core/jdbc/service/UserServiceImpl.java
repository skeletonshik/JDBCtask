package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    //private final UserDao dao = new UserDaoJDBCImpl();
    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        //dao.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        //dao.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //dao.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Пользователь с именем " + name + " был добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        //dao.removeUserById(id);
        userDaoHibernate.removeUserById(id);
        ;
    }

    public List<User> getAllUsers() throws SQLException {
        //return dao.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        //dao.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }


    @Override
  public void closeConnection() {
        userDaoHibernate.closeConnection();
    }
}
