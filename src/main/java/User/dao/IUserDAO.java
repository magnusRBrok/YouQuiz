package User.dao;

import User.DBUser;

public interface IUserDAO {

    DBUser getUser(int id);
    int addUser(DBUser user);
    void updateUser(int id, DBUser newUser);
    void deleteUser(int id);
    void executeQuery(String query);

}
