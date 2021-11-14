package User.dao;

import User.DBUser;
import User.dto.DBUserDto;
import User.dto.DBUserQuizzesDto;

import java.util.Collection;

public interface IUserDAO {

    DBUserQuizzesDto getUser(int id);
    Collection<DBUserQuizzesDto> getAllUsers();
    int addUser(DBUser user);
    void updateUser(int id, DBUser newUser);
    void deleteUser(int id);
    void executeQuery(String query);

}
