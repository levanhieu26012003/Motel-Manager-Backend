/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.Image;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface ImageRepository{
    void saveImage(Image i);
    Map<String, Object> getImageById(Long id);
    List<Image> getImageByMotel(Long molteId);
    void deleteImg(Long id);
    
}
