/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.User;
import com.lvh.services.FollowService;
import com.lvh.services.UserService;
import java.security.Principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/follows")
@CrossOrigin(origins = {"*"})
public class ApiFollowController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followSer;

    @PostMapping(path = "", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, Principal p) {
        try {
            User follower = this.userService.getUserByUserName(p.getName());
            Long followerId = follower.getId();
            Long hostId = Long.parseLong(params.get("hostId"));
            this.followSer.addFollow(followerId, hostId);

            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

    }

    @PostMapping(path = "/unfollow", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> remove(@RequestParam Map<String, String> params, Principal p) {
        User follower = this.userService.getUserByUserName(p.getName());
        Long followerId = follower.getId();
        Long hostId = Long.parseLong(params.get("hostId"));
        this.followSer.removeFollow(followerId, hostId);

        return new ResponseEntity<>("Removed", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/follower/{followerId}")
    @CrossOrigin
    public ResponseEntity<List<Map<String, Object>>> listFollower(@PathVariable(value = "followerId") Long id) {
        List<Map<String, Object>> userMaps = new ArrayList<>();
        for (User u : this.followSer.listFollower(id)) {
            userMaps.add(this.userService.ChangeJson(u));
        }

        return new ResponseEntity<>(userMaps, HttpStatus.OK);
    }

    @GetMapping("/followed/{followedId}")
    @CrossOrigin
    public ResponseEntity<List<Map<String, Object>>> listHost(@PathVariable(value = "followedId") Long id) {
        List<Map<String, Object>> userMaps = new ArrayList<>();
        for (User u : this.followSer.listHost(id)) {
            userMaps.add(this.userService.ChangeJson(u));
        }

        return new ResponseEntity<>(userMaps, HttpStatus.OK);
    }

}
