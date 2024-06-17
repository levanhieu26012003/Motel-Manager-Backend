/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.repositories.ImageRepository;
import com.lvh.repositories.MotelRepository;
import com.lvh.services.MotelService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
@Service
public class MotelServiceImpl
        implements MotelService {

    @Autowired
    private MotelRepository motelRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imgRepo;

    @Override
    public List<Map<String, Object>> getMotel(Map<String, String> params) {
        return this.motelRepo.getMotel(params);
    }

    @Override
    @Transactional
    public void addMotel(Motel motel) {

        this.motelRepo.saveMotel(motel);

        // Upload images to Cloudinary and save Image entities
        if (motel.getFiles().size() > 1) {
            System.out.println("VAOOO IFFFFFFFFF");
            System.out.println(motel.getFiles());
            System.out.println(motel.getFiles().size());
            System.out.println("VAOOO IFFFFFFFFF sauuuuuuuu");

            for (MultipartFile image : motel.getFiles()) {
                Map uploadResult;

                try {
                    uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                    String url = (String) uploadResult.get("url");
                    String publicId = (String) uploadResult.get("public_id");
                    Image img = new Image();
                    img.setMotelId(motel);
                    img.setUrl(url);
                    img.setPublicUrl(publicId);
                    imgRepo.saveImage(img);
                } catch (IOException ex) {
                    Logger.getLogger(MotelServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("VAOOO ELSEEEEE");
        }
    }

    @Override
    public Map<String, Object> getMotelById(Long id) {
        return this.motelRepo.getMotelById(id);
    }

    @Override
    public void deleteMotel(Long id) {
        this.motelRepo.deleteMotel(id);
    }

    @Override
    public void approvedMotel(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Motel getMotelByIdObject(Long id) {
        return this.motelRepo.getMotelByIdObject(id);
    }

}
