/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.pojo.User;
import com.lvh.repositories.MotelRepository;
import com.lvh.services.ImageService;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
    private UserService userService;

    @Autowired
    private Environment env;

    @Override
    public List<Motel> getMotel(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root r = q.from(Motel.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String wards = params.get("wards");
        if (wards != null && !wards.isEmpty()) {
            predicates.add(b.like(r.get("wards"), String.format("%%%s%%", wards)));
        }
        
        String district = params.get("district");
        if (district != null && !district.isEmpty()) {
            predicates.add(b.like(r.get("district"), String.format("%%%s%%", district)));
        }
        
        String province = params.get("province");
        if (province != null && !province.isEmpty()) {
            predicates.add(b.like(r.get("province"), String.format("%%%s%%", province)));
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
        return query.getResultList();  
    }

    @Override
    public void saveOrUpdateMotel(Motel m) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (m.getId() != null) {
            if(m.getStatus() == "PENDING")
                m.setStatus("APPROVED");
            s.update(m);
        } else {
            s.save(m);
        }
    }

    @Override
    public Motel getMotelById(Long id) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        return s.get(Motel.class, id);

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
    public Map<String, Object> motelToJson(Motel motel) {
        Map<String, Object> motelMap = new HashMap<>();
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

        return motelMap;
    }

    @Override
    public Motel jsonToMotel(Map<String, String> params, List<MultipartFile> files) {
        Motel u = new Motel();
        u.setTitle(params.get("title"));
        u.setArea(Float.parseFloat(params.get("area")));
        u.setPrice(Integer.parseInt(params.get("price")));
        u.setNumberTenant(Integer.parseInt(params.get("numberTenant")));
        u.setAddress(params.get("address"));
        u.setWards(params.get("wards"));
        u.setDistrict(params.get("district"));
            u.setProvince(params.get("province"));

        User user = this.userService.getUserByUserName(params.get("username"));
        u.setUserId(user);
        u.setFiles(files);
        return u;
    }

    
}
