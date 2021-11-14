package User.dao;

import User.DBUser;
import User.DBUserDto;

import java.util.Collection;

public interface IUserDAO {

    DBUserDto getUser(int id);
    Collection<DBUserDto> getAllUsers();
    int addUser(DBUser user);
    void updateUser(int id, DBUser newUser);
    void deleteUser(int id);
    void executeQuery(String query);

}
