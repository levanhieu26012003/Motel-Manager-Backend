/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.Comment;
import com.lvh.pojo.Motel;
import com.lvh.services.CommentService;
import com.lvh.services.MotelService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api/motels")
@CrossOrigin(origins = {"*"})
public class ApiMotelController {

    @Autowired
    private MotelService motelSer;
    
    @Autowired
    private CommentService cmtSer;

    @GetMapping("/{motelId}/comments")
    public ResponseEntity<List<Map<String, Object>>> listcmt(@RequestParam Map<String, String> params,@PathVariable(value = "motelId") Long id) {
        List<Map<String, Object>> cmtMaps = new ArrayList<>();
        for (Comment cmt : this.cmtSer.getComment(params, id)) {
            cmtMaps.add(this.cmtSer.cmtToJson(cmt));
        }

        return new ResponseEntity<>(cmtMaps, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> list(@RequestParam Map<String, String> params) {
        List<Map<String, Object>> motelMaps = new ArrayList<>();
        for (Motel motel : this.motelSer.getMotel(params)) {
            motelMaps.add(this.motelSer.motelToJson(motel));
        }
        return new ResponseEntity<>(motelMaps, HttpStatus.OK);
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<List<Map<String, Object>>> listByUsername(@PathVariable(value="username") String username) {
        List<Map<String, Object>> motelMaps = new ArrayList<>();
        for (Motel motel : this.motelSer.getMotelByUsername(username)) {
            motelMaps.add(this.motelSer.motelToJson(motel));
        }
        return new ResponseEntity<>(motelMaps, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Map<String, String> params, @RequestPart List<MultipartFile> files) {
        Motel motel = this.motelSer.jsonToMotel(params, files);
        this.motelSer.saveOrUpdateMotel(motel);
    }

    
    @DeleteMapping(path = "/{motelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotel(@PathVariable(value = "motelId") Long id) {
        
        this.motelSer.deleteMotel(id);
    }
}
