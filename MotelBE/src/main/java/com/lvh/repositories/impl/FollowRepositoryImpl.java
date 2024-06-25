/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.User;
import com.lvh.repositories.FollowRepository;
import com.lvh.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author levan
 */
@Repository
public class FollowRepositoryImpl implements FollowRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Transactional(readOnly = true)
    @Override
    public List<User> listFollower(Long id) {
        User user = this.userService.getUserById(id);

        return new ArrayList<>(user.getFollowers());

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listHost(Long id) {
        User user = this.userService.getUserById(id);

        return new ArrayList<>(user.getHosts());
    }

    @Transactional
    @Override
    public void addFollow(Long followerId, Long hostId) {
        User follower = userService.getUserById(followerId);
        User host = userService.getUserById(hostId);
        System.out.println("ADDD flowww");
        System.out.println(host.getUsername());
        System.out.println(follower.getUsername());
        
        follower.getFollowers().add(host);
        this.userService.addUser(follower);
    }
    @Transactional
    @Override
    public void removeFollow(Long followerId, Long hostId) {
        User follower = userService.getUserById(followerId);
        User host = userService.getUserById(hostId);

        follower.getFollowers().remove(host);
        this.userService.addUser(follower);
    }

}
