/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.lvh.controllers",
    "com.lvh.repositories",
    "com.lvh.services",
    "com.lvh.componants"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin().usernameParameter("username").passwordParameter("password");

//        http.authorizeRequests().antMatchers("/login", "/register", "/resources/**", "/css/**", "/js/**").permitAll()
//                .antMatchers("/**").hasRole("ADMIN") // Chỉ cho phép người dùng có role ADMIN truy cập vào trang admin
//                .anyRequest().authenticated();

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .loginPage("/login");

        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/**/add")
//                .access("hasRole('ROLE_ADMIN')");
//        .antMatchers("/**/pay")
//                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        http.csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/login", "/resources/**", "/css/**", "/js/**").permitAll() // Cho phép truy cập vào trang login và các tài nguyên tĩnh
//                .antMatchers("/**").hasRole("ADMIN") // Chỉ cho phép người dùng có role ADMIN truy cập vào trang admin
//                .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các yêu cầu khác
//                .and()
//                .formLogin()
//                .loginPage("/login") // Thiết lập trang login tùy chỉnh
//                .defaultSuccessUrl("/", true) // Chuyển hướng đến trang chủ sau khi đăng nhập thành công
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dlkfozznl",
                        "api_key", "216689541712763",
                        "api_secret", "m_IQI6lLGClNtrf8ZeuG97Do4Fs",
                        "secure", true));
        return cloudinary;
    }

    @Bean
    public JavaMailSender getJavaMailSend() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("levanhieu26012003@gmail.com");
        mailSender.setPassword("qjpw wfda arwp emls");

        Properties javaMailProperties = new Properties();

        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

}
