/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.User;
import java.util.List;

/**
 *
 * @author levan
 */
public interface FollowRepository {    
    List<User> listFollower(Long id);

    List<User> listHost(Long id);

    void addFollow(Long followerId, Long hostId);
    
    void removeFollow(Long followerId, Long hostId);
}
