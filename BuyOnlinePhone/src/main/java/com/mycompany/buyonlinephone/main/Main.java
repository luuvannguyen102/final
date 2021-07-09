/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.main;

import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.service.ProductService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("password===>" + encrytePassword("12345"));
       
      
        
    }

    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
    
}
