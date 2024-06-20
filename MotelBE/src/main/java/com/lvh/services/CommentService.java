/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services;

import com.lvh.pojo.Comment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface CommentService {

    Map<String, Object> cmtToJson(Comment cmt);

    Comment jsonToComment(Map<String, String> params);

    List<Comment> getComment(Map<String, String> params, Long id);

    void saveOrUpdateComment(Comment m);

    Comment getCommentById(Long id);

    void deleteComment(Long id);
}
