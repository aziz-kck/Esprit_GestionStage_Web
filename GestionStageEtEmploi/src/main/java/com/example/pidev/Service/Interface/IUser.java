package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.User;

import java.util.List;

public interface IUser {

    boolean checkEmailExists(String email);
    void add(User a);
    User edit(User a);
    List<User> selectAll();
    User SelectById(int id);
    void deleteById(int id);
    User getUserByEmail(String email);
}
