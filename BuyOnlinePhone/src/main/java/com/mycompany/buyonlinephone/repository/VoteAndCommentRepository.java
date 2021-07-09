/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.VoteAndCommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface VoteAndCommentRepository extends CrudRepository<VoteAndCommentEntity, Integer>{
    
//    
//    @Query(value = "select sum(vote) from vote where vote.product_id = ?", nativeQuery = true)
//    int vote (int productId);
}
