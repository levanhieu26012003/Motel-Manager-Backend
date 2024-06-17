/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services;

import java.util.List;

/**
 *
 * @author levan
 */
public interface StatsService {
   List<Object[]> statsUserByPeriod(int year, String period);
}
