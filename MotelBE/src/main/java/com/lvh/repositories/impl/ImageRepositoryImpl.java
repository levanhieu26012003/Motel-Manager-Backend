/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.repositories.ImageRepository;
import com.lvh.services.ImageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author levan
 */
@Repository
@Transactional
public class ImageRepositoryImpl implements ImageRepository{

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    @Autowired
    private ImageService imgSer;

    
    @Override
    public void saveImage(Image i) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.save(i);
    }

    @Override
    public List<Image> getImageByMotel(Long molteId) {
        Session s = this.factoryBean.getObject().openSession();
        Query q = s.createNamedQuery("Image.findByMotelId");
        q.setParameter("motelId", molteId);
        return q.getResultList();
    }
    
    
    @Override
    public Map<String, Object> getImageById(Long id) {
        Session s = this.factoryBean.getObject().openSession();
        Image img = s.get(Image.class, id);
        Map<String, Object> imgJs = new HashMap<>();
        if (img != null) {
            imgJs.put("id", img.getId());
            imgJs.put("url", img.getUrl());
            imgJs.put("public_url", img.getPublicUrl());
        } else {
            imgJs.put("error", "Motel not found with ID: " + id);
        }
        return imgJs;      
    }

    @Override
    public void deleteImg(Long id) {
        Session s = factoryBean.getObject().getCurrentSession();
        Image p = s.get(Image.class, id);
        if (p != null) {
            s.delete(p);
        } else {
            throw new RuntimeException("Image not found with ID: " + id);
        }
    }

    
    
   
}
