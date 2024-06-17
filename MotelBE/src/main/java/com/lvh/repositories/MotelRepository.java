/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import com.lvh.pojo.Motel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author levan
 */
public interface MotelRepository {

    public List<Map<String, Object>> getMotel(Map<String, String> params);

    void saveMotel(Motel m);

    Map<String, Object> getMotelById(Long id);
    
    Motel getMotelByIdObject(Long id);

    void deleteMotel(Long id);
}
