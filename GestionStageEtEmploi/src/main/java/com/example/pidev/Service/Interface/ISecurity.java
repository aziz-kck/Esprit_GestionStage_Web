package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.User;

import java.util.List;

public interface ISecurity {

    User getUser(String username);
    List<User> getUsers();
    public String getCurrentUserName();
}
