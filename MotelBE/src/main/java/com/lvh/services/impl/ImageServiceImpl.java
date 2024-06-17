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
import com.lvh.services.ImageService;
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
public class ImageServiceImpl
        implements ImageService {
    
    @Autowired
    private ImageRepository ImaRepo;
    
    @Override
    public void saveImage(Image i) {
        this.ImaRepo.saveImage(i);
    }
    
    @Override
    public List<Image> getImageByMotel(Long molteId) {
        return this.ImaRepo.getImageByMotel(molteId);
    }
    
    @Override
    public Map<String, Object> getImageById(Long id) {
        return this.ImaRepo.getImageById(id);
    }
    
    @Override
    public void deleteImage(Long id) {
        this.ImaRepo.deleteImg(id);
    }
    
}
