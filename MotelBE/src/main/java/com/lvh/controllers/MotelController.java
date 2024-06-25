/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.Motel;
import com.lvh.services.MotelService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author levan
 */
@Controller
public class MotelController {
    @Autowired
    private MotelService motelService;
    
    @GetMapping("/motels")
    public String createView(Model model) {
        model.addAttribute("motel", new Motel());
        return "motels"; // Tên định nghĩa trong tiles.xml
    }
    
    
    
    @PostMapping("/motels")
    public String createProduct(@ModelAttribute(value ="motel")Motel m) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Người dùng đã đăng nhập
            try {
                this.motelService.saveOrUpdateMotel(m);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        return "motels";
        } else {
            return "login";
        }
        
    }
    
    @GetMapping("/motels/{motelId}")
    public String updateView(Model model, @PathVariable(value = "motelId") Long id) {
        model.addAttribute("motel", this.motelService.getMotelById(id));
        return "motels";
    }
    
    @GetMapping("/motels/delete/{motelId}")
    public String deleteMotel(Model model, @PathVariable(value = "motelId") Long id) {
        this.motelService.deleteMotel(id);
        return "redirect:/";
    }
    
}
