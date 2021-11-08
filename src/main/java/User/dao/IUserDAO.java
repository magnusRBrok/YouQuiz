package User.dao;

import User.DBUser;

public interface IUserDAO {

    DBUser getUser(int id);
    int addUser(DBUser user);
    void deleteUser(int id);

}
