/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;


import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.enums.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 *
 * @author PC
 */
@Entity
@Table(name = "accountRole")
public class AccountRoleEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;
    
    @ManyToMany(mappedBy = "accountRoles")
    private Set<AccountEntity> accounts;

    public AccountRoleEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }

   
}
