/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Comment;
import com.lvh.repositories.CommentRepository;
import com.lvh.services.MotelService;
import com.lvh.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author levan
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private MotelService motelService;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Override
    public List<Comment> getComment(Map<String, String> params, Long id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = builder.createQuery(Comment.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("houseId"), id)); // Lọc theo houseId

        Query<Comment> query = session.createQuery(criteriaQuery);

        if (params.get("page") != null) {
            int page = Integer.parseInt(params.get("page"));
            int pageSize = Integer.parseInt(env.getProperty("cmts.pageSize")); // Lấy kích thước trang từ properties
            int start = (page - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public void saveOrUpdateComment(Comment m) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (m.getId() != null) {
            s.update(m);
        } else {
            s.save(m);
        }
    }

    @Override
    public Comment getCommentById(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        return s.get(Comment.class, id);
    }

    public void deleteComment(Long id) {
        Session s = factoryBean.getObject().getCurrentSession();
        Comment p = s.get(Comment.class, id);
        if (p != null) {
            s.delete(p);
        } else {
            throw new RuntimeException("Comment not found with ID: " + id);
        }
    }

    @Override
    public Map<String, Object> cmtToJson(Comment cmt) {
        Map<String, Object> cmtMap = new HashMap<>();
        cmtMap.put("id", cmt.getId());
        cmtMap.put("content", cmt.getContent());
        cmtMap.put("user", this.userService.ChangeJson(cmt.getUserId()));

        return cmtMap;
    }

    @Override
    public Comment jsonToComment(Map<String, String> params) {
        Comment cmt = new Comment();
        cmt.setContent(params.get("content"));
        if (params.get("motel") != null) {
            cmt.setHouseId(this.motelService.getMotelById(Long.parseLong(params.get("motel"))));
        }
        if (params.get("username") != null) {
            cmt.setUserId(this.userService.getUserByUserName((params.get("username"))));
        }

        return cmt;
    }

}
