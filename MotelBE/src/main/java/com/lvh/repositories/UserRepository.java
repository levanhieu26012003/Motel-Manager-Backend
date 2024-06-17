/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface UserRepository {
    List<User> getUsers();
    User getUserByUserName(String username);
    Map<String, Object> ChangeJson(User user);
    void addUser(User user);
    boolean authUser(String username, String password);
}
