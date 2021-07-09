/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;


import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.enums.Gender;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AccountEntity extends Personal implements Serializable{
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.ACTIVE;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrdersEntity> orders;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VoteAndCommentEntity> voteAndComments;
    
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "account_accountRole_relationship",
            joinColumns = @JoinColumn(name = "account_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "accountRole_id",
                    referencedColumnName = "id"))
    private Set<AccountRoleEntity> accountRoles;

    public AccountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public Set<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrdersEntity> orders) {
        this.orders = orders;
    }

    public Set<VoteAndCommentEntity> getVoteAndComments() {
        return voteAndComments;
    }

    public void setVoteAndComments(Set<VoteAndCommentEntity> voteAndComments) {
        this.voteAndComments = voteAndComments;
    }

   
    public Set<AccountRoleEntity> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRoleEntity> accountRoles) {
        this.accountRoles = accountRoles;
    }

    
}
