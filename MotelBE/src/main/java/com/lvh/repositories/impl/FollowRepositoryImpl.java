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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        return new ArrayList<>(user.getUserCollection());

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listHost(Long id) {
        User user = this.userService.getUserById(id);

        return new ArrayList<>(user.getUserCollection1());
    }

    @Transactional(readOnly = true)
    @Override
    public void addFollow(Long followerId, Long followedId) {
        User follower = userService.getUserById(followerId);
        User followed = userService.getUserById(followedId);

        follower.getUserCollection().add(followed);
        this.userService.addUser(follower);
    }
    @Transactional(readOnly = true)
    @Override
    public void removeFollow(Long followerId, Long followedId) {
        User follower = userService.getUserById(followerId);
        User followed = userService.getUserById(followedId);

        follower.getUserCollection().remove(followed);
        this.userService.addUser(follower);
    }

}
