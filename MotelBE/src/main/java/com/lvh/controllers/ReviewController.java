/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;

import com.lvh.pojo.Reviews;
import com.lvh.pojo.User;
import com.lvh.services.ReviewService;
import com.lvh.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author levan
 */
@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @RequestMapping("/reviews")
    public String createReviews(Model model) {
        Map<String, Object> atb = new HashMap<>();
        List<Map<String, Object>> avgs = new ArrayList<>();
        List<User> users = this.userService.getUsersByRole("ROLE_USER_HOST");
        for (User u : users) {
            Map<String, Object> um = this.userService.ChangeJson(u);

            um.put("avg", 5);
            avgs.add(um);
        }
        atb.put("users", avgs);

        model.addAllAttributes(atb);
        return "reviews";
    }

    @GetMapping("/review")
    public String createReview(Model model) {
                model.addAttribute("user", new User());
        return "review"; // Tên định nghĩa trong tiles.xml
    }

    @GetMapping("/review/{id}")
    public String updateView(Model model, @PathVariable(value = "id") Long id) {
        Map<String, Object> atb = new HashMap<>();
        atb.put("user", this.userService.getUserById(id));
        atb.put("reviews", this.reviewService.getReviews(id));
        atb.put("avg", this.reviewService.averageRating(id));
        model.addAllAttributes(atb);
        return "review";
    }
}
