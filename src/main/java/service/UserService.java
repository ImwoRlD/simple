package service;

import model.User;

public interface UserService {
    public User login(String username,String userpassword);
}
