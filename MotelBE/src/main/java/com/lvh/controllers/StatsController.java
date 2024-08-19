/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.controllers;



import com.lvh.services.StatsService;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;

    
    @GetMapping("/stats")
    public String statsView(Model model,@RequestParam Map<String, String> params) {
        String year = params.getOrDefault("year", String.valueOf(LocalDate.now().getYear()));
        String period = params.getOrDefault("period", "MONTH");
        model.addAttribute("statsUser", statsService.statsUserByPeriod(Integer.parseInt(year), period));
        
        return "stats";
        
    }
}
