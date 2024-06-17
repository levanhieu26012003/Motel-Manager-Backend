/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.repositories;

import java.util.List;

/**
 *
 * @author levan
 */
public interface StatsRepository {
    List<Object[]> statsUserByPeriod(int year, String period);
}
