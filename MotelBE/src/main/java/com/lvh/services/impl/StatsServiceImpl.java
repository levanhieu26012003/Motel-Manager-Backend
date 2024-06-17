/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;


import com.lvh.repositories.StatsRepository;
import com.lvh.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepo;


    public List<Object[]> statsUserByPeriod(int year, String period){
        return this.statsRepo.statsUserByPeriod(year, period);
    }
    
}
