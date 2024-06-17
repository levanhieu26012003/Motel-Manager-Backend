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
    public void addUser(User user) {
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
        
        if ( q.getSingleResult() == null)
            return null;
        
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
        userMap.put("numberTenant", user.getUserRole());
        userMap.put("address", user.getAvatar());

        return userMap;
    }

}
