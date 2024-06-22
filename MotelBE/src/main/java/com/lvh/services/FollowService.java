/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services;

import com.lvh.pojo.User;
import java.util.List;

/**
 *
 * @author levan
 */
public interface FollowService {

    List<User> listFollower(Long id);

    List<User> listHost(Long id);

    void addFollow(Long followerId, Long followedId);

    void removeFollow(Long followerId, Long followedId);

}
