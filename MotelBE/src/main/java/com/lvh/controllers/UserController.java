/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.User;
import com.lvh.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author levan
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/users")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.getUsers());
        return "users";
    }
    
    @GetMapping("/user")
    public String createView(Model model) {
        model.addAttribute("user", new User());
        return "user"; // Tên định nghĩa trong tiles.xml
    }
    
    @GetMapping("/user/{username}")
    public String updateView(Model model, @PathVariable(value = "username") String username) {
        model.addAttribute("user", this.userService.getUserByUserName(username));
        return "user";
    }
    
    @PostMapping("/user")
    public String createUser(@ModelAttribute(value ="user")User u) {
        try {
                this.userService.addUser(u);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        return "user";
    }
    
    @GetMapping("/user/delete/{username}")
    public String delete(Model model, @PathVariable(value = "username") String username) {
        this.userService.deleteUserByUsername(username);
        return "redirect:/users";
    }
    
    @GetMapping("/user/active/{username}")
    public String changeActive(Model model, @PathVariable(value = "username") String username) {
        this.userService.changeActive(username);
        return "redirect:/reviews";
    }
}
