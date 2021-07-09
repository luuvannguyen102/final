/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.CategoryEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>{
    
    @Query(value = "Select c from CategoryEntity c"
            + " JOIN FETCH c.products")
    Set<CategoryEntity> getAll();
    
    @Query(value = "Select c from CategoryEntity c"
            + " JOIN FETCH c.products"
            + " where c.name = ?1")
    Set<CategoryEntity> getName(String name);
}
