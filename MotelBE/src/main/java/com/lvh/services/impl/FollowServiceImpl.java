/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.lvh.pojo.User;
import com.lvh.repositories.FollowRepository;
import com.lvh.services.FollowService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author levan
 */
@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private FollowRepository followRepo;
    @Override
    public List<User> listFollower(Long id) {
        return this.followRepo.listFollower(id);
    }

    @Override
    public List<User> listHost(Long id) {
        return this.followRepo.listHost(id);
    }

    @Override
    public void addFollow(Long followerId, Long hostId) {
        this.followRepo.addFollow(followerId, hostId);
    }

    @Override
    public void removeFollow(Long followerId, Long hostId) {
        this.followRepo.removeFollow(followerId, hostId);
    }
    
}
