package dao;

import model.User;

public interface UserDao {
    public User login(String username,String userpassword);
}
