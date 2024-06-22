 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;


import com.lvh.pojo.Comment;
import com.lvh.services.CommentSecurityService;
import com.lvh.services.CommentService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = {"*"})
public class ApiCommentController {

    @Autowired
    private CommentService cmtSer;
    
    @Autowired
    private CommentSecurityService cmtSecuSer;

    @PostMapping(path = "/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody Map<String, String> params) {
        this.cmtSer.saveOrUpdateComment(this.cmtSer.jsonToComment(params));
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteMotel(@PathVariable(value="commentId") Long id, Principal p) {
        Comment comment = this.cmtSer.getCommentById(id);
        if (comment.getUserId() ==  null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String currentUsername = p.getName();
        if (comment.getUserId().getUsername().equals(currentUsername)){
            this.cmtSer.deleteComment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
