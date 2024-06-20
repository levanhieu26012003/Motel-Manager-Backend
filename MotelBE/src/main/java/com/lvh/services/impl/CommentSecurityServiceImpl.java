/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.lvh.pojo.Comment;
import com.lvh.repositories.CommentRepository;
import com.lvh.services.CommentSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author levan
 */
@Service
public class CommentSecurityServiceImpl implements CommentSecurityService{

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public boolean isCommentOwner(Authentication authentication, Long commentId) {
        Comment comment =this.commentRepository.getCommentById(commentId);
        String currentUsername = authentication.getName();
        return comment.getUserId().getUsername().equals(currentUsername);
    }
    
}
