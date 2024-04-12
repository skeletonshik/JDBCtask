package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final  Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE USERS (" +
                "  `ID` BIGINT(200) NOT NULL AUTO_INCREMENT," +
                "  `NAME` VARCHAR(50) NULL," +
                "  `LAST_NAME` VARCHAR(50) NULL," +
                "  `AGE` TINYINT(100) NULL," +
                "  PRIMARY KEY (`ID`));";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            System.out.println("New table was created");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка");
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }



    public void dropUsersTable() throws SQLException {

        try (Statement dropTable = connection.createStatement()){
            connection.setAutoCommit(false);
            dropTable.executeUpdate("DROP TABLE USERS");
        } catch (SQLException e)    {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO USERS (NAME, LAST_NAME, AGE) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            connection.rollback();
        }   finally {
            connection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID =?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            connection.rollback();
        }   finally {
            connection.setAutoCommit(true);
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LAST_NAME, AGE FROM USERS";

        try (Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())    {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        }  catch (SQLException e)   {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return userList;
    }


    public void cleanUsersTable() throws SQLException {
        String sql = "TRUNCATE TABLE USERS";
        try (Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.execute(sql);
        } catch (SQLException e)    {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
    public void closeConnection ()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
