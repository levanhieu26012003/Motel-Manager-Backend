/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.services.MotelService;
import com.lvh.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author levan
 */
@Controller
@ControllerAdvice
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("users", this.userService.getUsers());
    }
    
    @RequestMapping("/users")
    public String index(Model model,@RequestParam Map<String, String> params){
        model.addAttribute("users", this.userService.getUsers()); 
            return "users";
    }
}
