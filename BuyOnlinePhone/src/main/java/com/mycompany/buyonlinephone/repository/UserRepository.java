/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;



import com.mycompany.buyonlinephone.entities.AccountEntity;
import com.mycompany.buyonlinephone.enums.CommonStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AccountEntity, Integer> {

    AccountEntity findByEmailLikeAndStatusLike(String email,
            CommonStatus status);
    
    AccountEntity findByEmailLike(String email
            );
    
    @Query(value = "Select a from AccountEntity a"
            + " LEFT JOIN FETCH a.voteAndComments"
            + " LEFT JOIN FETCH a.orders ao"
            + " LEFT JOIN FETCH ao.ordersDetails"
            + " where a.email = ?1")
    AccountEntity getAccount (String email);
}
