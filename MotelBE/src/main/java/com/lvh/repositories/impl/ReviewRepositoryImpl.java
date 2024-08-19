/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Reviews;
import com.lvh.repositories.ReviewRepository;
import com.lvh.services.MotelService;
import com.lvh.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private MotelService motelService;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Override
    public List<Reviews> getReviews(Long hostId) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reviews> criteriaQuery = builder.createQuery(Reviews.class);
        Root<Reviews> root = criteriaQuery.from(Reviews.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("hostId"), hostId)); // Lọc theo houseId

        Query<Reviews> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public void saveOrUpdateReview(Reviews r) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (r.getId() != null) {
            s.update(r);
        } else {
            s.save(r);
        }
    }

    @Override
    public Reviews getReviewById(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Reviews.class, id);
    }

    public void deleteReview(Long id) {
        Session s = factoryBean.getObject().getCurrentSession();
        Reviews p = s.get(Reviews.class, id);
        if (p != null) {
            s.delete(p);
        } else {
            throw new RuntimeException("Reviews not found with ID: " + id);
        }
    }

    @Override
    public Map<String, Object> reviewToJson(Reviews r) {
        Map<String, Object> rs = new HashMap<>();
        rs.put("id", r.getId());
        rs.put("comment", r.getComment());
        rs.put("rating", r.getRating());
        rs.put("hostId", r.getHostId());
        rs.put("tenantId", r.getTenantId());

        return rs;
    }

    @Override
    public Reviews jsonToReview(Map<String, String> params) {
        Reviews r = new Reviews();
        r.setComment(params.get("comment"));
        r.setRating(Integer.parseInt(params.get("rating")));
        r.setHostId(this.userService.getUserById(Long.parseLong(params.get("hostId"))));
        r.setTenantId(this.userService.getUserById(Long.parseLong(params.get("tenantId"))));
        return r;
    }

    @Override
    public double averageRating(Long id) {
        List<Reviews> reviews = this.getReviews(id);
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream().mapToDouble(Reviews::getRating).sum();
        return sum / reviews.size();
    }


    
}
