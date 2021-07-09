/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.CategoryEntity;
import com.mycompany.buyonlinephone.repository.CategoryRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Set<CategoryEntity> getCategory () {
        return (Set<CategoryEntity>) categoryRepository.getAll();
    }
    
    public Set<CategoryEntity> getCategoryByName (String name) {
        return (Set<CategoryEntity>) categoryRepository.getName(name);
    }
}
