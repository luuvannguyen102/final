/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;

import com.mycompany.buyonlinephone.enums.CommonStatus;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "creditCard")
public class CreditCardEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
   
    private String name;
    
    
    private int cardNumber;
    
    
    private String cvcCode;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
    
    
    private double balance;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "exp_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate;
    
    
    @OneToMany(mappedBy = "creditCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PaymentEntity> payments;

    public CreditCardEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public void setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Set<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentEntity> payments) {
        this.payments = payments;
    }

   
}
