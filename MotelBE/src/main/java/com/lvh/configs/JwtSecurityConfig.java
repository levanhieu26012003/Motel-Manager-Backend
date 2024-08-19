/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.configs;

import com.lvh.filters.CustomAccessDeniedHandler;
import com.lvh.filters.JwtAuthenticationTokenFilter;
import com.lvh.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author huu-thanhduong
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.lvh.controllers",
    "com.lvh.repositories",
    "com.lvh.services",
    "com.lvh.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
//        http.authorizeRequests().antMatchers("/api/users").permitAll(); // cho phép tạo account
//        http.authorizeRequests().antMatchers("/api/login").permitAll(); // cho phép đăng nhập
//        http.authorizeRequests().antMatchers("/api/user/{username}").permitAll(); // lấy thông tin user hiển thị công khai
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments").permitAll(); // cho phép lấy cmnt
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/motels").permitAll(); // cho phép lấy Xem motels 
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/motels").hasRole("USER_HOST"); 
//        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER_TENANT') or hasRole('ROLE_USER_HOST')")
//                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER_TENANT') or hasRole('ROLE_USER_HOST')")
//                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER_TENANT') or hasRole('ROLE_USER_HOST')").and()
//                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        
        

        http.authorizeRequests().antMatchers("/").permitAll(); // cho phép tạo account
    }
}
