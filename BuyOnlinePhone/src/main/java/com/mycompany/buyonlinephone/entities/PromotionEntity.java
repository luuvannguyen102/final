/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;


import com.mycompany.buyonlinephone.enums.PromotionStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "promotion")
public class PromotionEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    
    @NotBlank
    @Size(min = 3, max = 5000)
    private String description;
    
    @NotBlank
    private String image;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PromotionStatus status;
         
    @Column(nullable = false)
    private int discountPercent;
    
    private int quantity;
    
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startDate;
    
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDate;
    
    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CodeVoucherEntity> codeVouchers;
    
    public PromotionEntity() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PromotionStatus getStatus() {
        return status;
    }

    public void setStatus(PromotionStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<CodeVoucherEntity> getCodeVouchers() {
        return codeVouchers;
    }

    public void setCodeVouchers(Set<CodeVoucherEntity> codeVouchers) {
        this.codeVouchers = codeVouchers;
    }

   
    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }   

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

   
   
    
     public String randomAlphaNumeric(int numberOfCharactor) {        
        String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
        String alphaUpperCase = alpha.toUpperCase(); // A-Z
        String digits = "0123456789"; // 0-9
        String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;       
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    
     public  int randomNumber(int min, int max) {
         Random generator = new Random();
        return generator.nextInt((max - min) + 1) + min;
    }
}
