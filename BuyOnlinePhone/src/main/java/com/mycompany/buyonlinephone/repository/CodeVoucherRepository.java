/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.CodeVoucherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface CodeVoucherRepository extends CrudRepository<CodeVoucherEntity, Integer>{
    
    @Query(value = "Select c from CodeVoucherEntity c"
            + " where c.name = ?1")
    CodeVoucherEntity findCodeVoucherByName (String code) ;
}
