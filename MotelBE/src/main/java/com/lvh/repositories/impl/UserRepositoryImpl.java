/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.User;
import com.lvh.repositories.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers() {
        Session s = this.factoryBean.getObject().openSession();
        Query q = s.createNamedQuery("User.findAll");
        return q.getResultList();
    }
    
     @Override
    public List<User> getUsersByRole(String role) {
        Session s = this.factoryBean.getObject().openSession();
        Query q = s.createNamedQuery("User.findByUserRole");
        q.setParameter("userRole", role);
        return q.getResultList();
    }

    @Override
    public void saveOrUpdateUser(User user) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (user.getId() != null) {
            s.update(user);
        } else {
            user.setPassword(this.passEncoder.encode(user.getPassword()));
            s.save(user);
        }
    }

    @Override
    public User getUserByUserName(String username) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUserName(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public Map<String, Object> ChangeJson(User user) {
        Map<String, Object> userMap = new HashMap<>();

        userMap.put("id", user.getId());
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("userRole", user.getUserRole());
        userMap.put("avatar", user.getAvatar());
        userMap.put("active", user.getActive());
        return userMap;
    }

    @Override
    public User jsonToUser(Map<String, String> params, MultipartFile[] file) {
        User u = new User();
        u.setUsername(params.get("username"));
        u.setPassword(params.get("password"));

        if (params.get("email") != null) {
            u.setEmail(params.get("email"));
        }
        if (params.get("userRole") != null) {
            u.setEmail(params.get("userrole"));
        }
        u.setActive(true);
        if (file != null) {
            u.setFile(file[0]);
        }

        return u;
    }

    @Override
    public User getUserById(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE id = :id");
        q.setParameter("id", id);

        return (User) q.getSingleResult();
    }

    @Override
    public void deleteUser(String username) {
        Session s = factoryBean.getObject().getCurrentSession();
        User u = this.getUserByUserName(username);
        if (u != null) {
            s.delete(u);
        } else {
            throw new RuntimeException("User not found with ID: " + username);
        }
    }

    @Override
    public void changeActive(String username) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        
        User user = this.getUserByUserName(username);
        if(user != null)
        {
            if(user.getActive())
                user.setActive(Boolean.FALSE);
            else
                 user.setActive(Boolean.TRUE);
            this.saveOrUpdateUser(user);
        }
    }
}
