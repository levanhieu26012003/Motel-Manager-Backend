/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lvh.pojo.Image;
import com.lvh.pojo.Comment;
import com.lvh.repositories.ImageRepository;
import com.lvh.repositories.CommentRepository;
import com.lvh.services.CommentService;
import com.lvh.services.CommentService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author levan
 */
@Service
public class CommentServiceImpl
        implements CommentService {

    @Autowired
    private CommentRepository cmtRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imgRepo;

    @Override
    public List<Comment> getComment(Map<String, String> params,Long id) {
        return this.cmtRepo.getComment(params, id);
    }

    @Override
    @Transactional
    public void saveOrUpdateComment(Comment cmt) {

        this.cmtRepo.saveOrUpdateComment(cmt);

    }

    @Override
    public void deleteComment(Long id) {
        this.cmtRepo.deleteComment(id);
    }

    @Override
    public Comment getCommentById(Long id) {
        return this.cmtRepo.getCommentById(id);
    }

    @Override
    public Map<String, Object> cmtToJson(Comment cmt) {
        return this.cmtRepo.cmtToJson(cmt);
    }

    @Override
    public Comment jsonToComment(Map<String, String> params) {
        return this.cmtRepo.jsonToComment(params);
    }

}
