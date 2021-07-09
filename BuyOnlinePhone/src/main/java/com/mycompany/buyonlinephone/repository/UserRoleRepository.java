/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;



import com.mycompany.buyonlinephone.entities.AccountRoleEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<AccountRoleEntity, Integer> {

    Set<AccountRoleEntity> findByAccounts_Email(String email);
}
