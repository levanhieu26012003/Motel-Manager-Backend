/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.repositories.MotelRepository;
import com.lvh.services.ImageService;
import com.lvh.services.MotelService;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author levan
 */
@Repository
@Transactional
public class MotelRepositoryImpl implements MotelRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private ImageService imgService;

    @Autowired
    private MotelService motelService;
    
    @Autowired
    private Environment env;

    @Override
    public List<Map<String, Object>> getMotel(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root r = q.from(Motel.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();
        
        String kw = params.get("wards");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("wards"), String.format("%%%s%%", kw)));
        }
        
        String fromPrice = params.get("fromPrice");
        if (fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }
        
        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));
        
        Query query = s.createQuery(q);
        
        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("motel.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        // viết json
        List<Motel> motels = query.getResultList();
        List<Map<String, Object>> rs = new ArrayList<>();
        for (Motel motel : motels) {
            Map<String, Object> motelMap = this.motelService.getMotelById(motel.getId());
            rs.add(motelMap);
        }
        return rs;
    }

    @Override
    public void saveMotel(Motel m) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (m.getId() != null) {
            s.update(m);
        } else {
            s.save(m);
        }
    }

    @Override
    public Map<String, Object> getMotelById(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        Motel motel = s.get(Motel.class, id);
        Map<String, Object> motelMap = new HashMap<>();
        if (motel != null) {
            motelMap.put("id", motel.getId());
            motelMap.put("title", motel.getTitle());
            motelMap.put("price", motel.getPrice());
            motelMap.put("numberTenant", motel.getNumberTenant());
            motelMap.put("address", motel.getAddress());
            motelMap.put("district", motel.getDistrict());
            motelMap.put("province", motel.getProvince());
            motelMap.put("wards", motel.getWards());
            motelMap.put("createdDate", motel.getCreatedDate());
            motelMap.put("updatedDate", motel.getUpdatedDate());
            motelMap.put("username", motel.getUserId().getUsername());

            List<Map<String, Object>> imageCollection = new ArrayList<>();
            for (Image img : motel.getImageCollection()) {
                imageCollection.add(this.imgService.getImageById(img.getId()));
            }
            motelMap.put("imageCollection", imageCollection);
        } else {
            motelMap.put("error", "Motel not found with ID: " + id);
        }
        return motelMap;
    }

    public void deleteMotel(Long id) {
        Session s = factoryBean.getObject().getCurrentSession();
        Motel p = s.get(Motel.class, id);
        if (p != null) {
            s.delete(p);
        } else {
            throw new RuntimeException("Motel not found with ID: " + id);
        }
    }

    @Override
    public Motel getMotelByIdObject(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        return s.get(Motel.class, id);
    }
}
