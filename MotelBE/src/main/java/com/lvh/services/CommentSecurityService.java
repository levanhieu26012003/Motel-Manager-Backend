/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services;

import org.springframework.security.core.Authentication;

/**
 *
 * @author levan
 */
public interface CommentSecurityService {
    boolean isCommentOwner(Authentication authentication, Long commentId);
}
