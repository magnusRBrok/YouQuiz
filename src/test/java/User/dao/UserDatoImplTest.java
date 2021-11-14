package User.dao;

import User.DBUser;
import User.DBUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDatoImplTest {

    private IUserDAO userDAO = new UserDAOImpl();

    @BeforeEach
    void setUp() {
        //TODO: It's probably very wrong to use the DAO classes here to seed the data before testing the DAO class.
        //But for now, it seems to solve the issues of dealing with auto incrementing id's between tests.
        userDAO.addUser(new DBUser("test user 1"));
        userDAO.addUser(new DBUser("test user 2"));
    }

    @AfterEach
    public void clearDataFromDatabase() {
        userDAO.executeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT");
        userDAO.executeQuery("ALTER SEQUENCE my_seq RESTART WITH 1");
    }


    @Test
    void getUser() {
        DBUserDto user = userDAO.getUser(1);
        assertEquals("test user 1", user.getFirst_name());

        assertThrows(NotFoundException.class, () -> {
            userDAO.getUser(1000);
        });
    }

    @Test
    void addUser() {
        int id = userDAO.addUser(new DBUser("new user"));
        assertEquals(3, id);

        id = userDAO.addUser(new DBUser("new user2"));
        assertEquals(4, id);
    }

    @Test
    void addAndGetUser() {
        int id = userDAO.addUser(new DBUser("new user"));
        DBUserDto user = userDAO.getUser(id);
        assertEquals("new user", user.getFirst_name());
    }

    @Test
    void updateUser() {
        userDAO.updateUser(2, new DBUser("new name"));
        DBUserDto user = userDAO.getUser(2);
        assertEquals("new name", user.getFirst_name());
    }

    @Test
    void deleteUser() {
        userDAO.deleteUser(2);
        assertThrows(NotFoundException.class, () -> {
            userDAO.getUser(2);
        });
    }

}
