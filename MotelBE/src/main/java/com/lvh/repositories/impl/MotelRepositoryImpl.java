/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories.impl;

import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.pojo.User;
import com.lvh.repositories.MotelRepository;
import com.lvh.services.FollowService;
import com.lvh.services.ImageService;
import com.lvh.services.MailService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class MotelRepositoryImpl implements MotelRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Autowired
    private ImageService imgService;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @Autowired
    private MailService mailSer;
    
    @Autowired
    private FollowService followlSer;

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
        String sort = params.get("sort")== null ? "id":params.get("sort");

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get(sort)));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("motels.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }
    
    @Override
    public Map<String, Object> getMotelAPI(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Motel> q = b.createQuery(Motel.class);
        Root<Motel> r = q.from(Motel.class);
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
        String sort = params.get("sort")== null ? "id":params.get("sort");
        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }
        q.where(predicates.toArray(new Predicate[0]));
        q.orderBy(b.desc(r.get(sort)));

        Query<Motel> query = s.createQuery(q);

        String p = params.get("page");
        int page = p != null && !p.isEmpty() ? Integer.parseInt(p) : 1;
        int pageSize = Integer.parseInt(env.getProperty("motels.pageSize", "10"));
        int start = (page - 1) * pageSize;

        query.setFirstResult(start);
        query.setMaxResults(pageSize);

        List<Motel> motels = query.getResultList();
        
        List<Map<String, Object>> motelsMap = new ArrayList<>();
        for(Motel m:motels){
            motelsMap.add(this.motelToJson(m));
        }

        // Tính tổng số phần tử
        CriteriaQuery<Long> countQuery = b.createQuery(Long.class);
        Root<Motel> countRoot = countQuery.from(Motel.class);
        countQuery.select(b.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        long totalElements = s.createQuery(countQuery).getSingleResult();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        // Tạo phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("data", motelsMap);
        response.put("page", page);
        response.put("size", pageSize);
        response.put("totalElements", totalElements);
        response.put("totalPages", totalPages);
        response.put("first", page == 1);
        response.put("last", page == totalPages);
        response.put("nextPage", page < totalPages ? page + 1 : null);
        response.put("previousPage", page > 1 ? page - 1 : null);

        return response;
    }


    @Override
    public void saveOrUpdateMotel(Motel m) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        
        if (m.getId() != null) {
            if (m.getStatus().equals("PENDING")) {
                m.setStatus("APPROVED");
                User userHost = this.userService.getUserById(m.getUserId().getId());
                System.out.println("INN USERRR");
                System.out.println(userHost.getUsername());
                for (User user : this.followlSer.listHost(userHost.getId())) {
                        String subject = "New post !!!";
                        String text = "Hi " + user.getUsername() +"\nYour host is " + userHost.getUsername() + " has been post a new motel. Check it out!";
                    this.mailSer.sendMail(user.getEmail(), subject, text);
                }
            }
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
        motelMap.put("area", motel.getArea());
        motelMap.put("price", motel.getPrice());
        motelMap.put("numberTenant", motel.getNumberTenant());
        motelMap.put("address", motel.getAddress());
        motelMap.put("district", motel.getDistrict());
        motelMap.put("province", motel.getProvince());
        motelMap.put("wards", motel.getWards());
        motelMap.put("createdDate", motel.getCreatedDate());
        motelMap.put("updatedDate", motel.getUpdatedDate());
        motelMap.put("username", this.userService.ChangeJson(this.userService.getUserById(motel.getUserId().getId())));

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

    @Override
    @Transactional
    public List<Motel> getMotelByUsername(String username) {
        User u = this.userService.getUserByUserName(username);
        List<Motel> rs = new  ArrayList<>(u.getMotelCollection());
        return rs;
        
    }

}
