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
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/comments")
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
    public void create(@RequestParam Map<String, String> params) {
        this.cmtSer.saveOrUpdateComment(this.cmtSer.jsonToComment(params));
    }

    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteMotel(@PathVariable(value="commentId") Long id, Principal p) {
        Comment comment = this.cmtSer.getCommentById(id);
        String currentUsername = p.getName();
        System.out.println("zzzzzzzzzzzzzzzzzz");
        System.out.println(comment.getUserId().getUsername());
        System.out.println("bbbbbbbbbb");
        System.out.println(currentUsername);
        if (comment.getUserId().getUsername().equals(currentUsername)){
            this.cmtSer.deleteComment(id);
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.badRequest().body(id);

    }
}
