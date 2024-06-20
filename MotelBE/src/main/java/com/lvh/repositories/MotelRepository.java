/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.Motel;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
public interface MotelRepository {
    Map<String, Object> motelToJson(Motel motel);
    Motel jsonToMotel(Map<String, String> params,List<MultipartFile> files);
    List<Motel> getMotel(Map<String, String> params);
    void saveOrUpdateMotel(Motel m);
    Motel getMotelById(Long id);
    void deleteMotel(Long id);    
}
