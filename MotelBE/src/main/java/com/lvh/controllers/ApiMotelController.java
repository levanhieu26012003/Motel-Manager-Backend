/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;


import com.lvh.pojo.Motel;
import com.lvh.services.MotelService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiMotelController {
    @Autowired
    private MotelService motelSer;
    
    
    @GetMapping("/motels/")
    @CrossOrigin
    public ResponseEntity<List<Map<String, Object>>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.motelSer.getMotel(params), HttpStatus.OK);
    }
    

}
