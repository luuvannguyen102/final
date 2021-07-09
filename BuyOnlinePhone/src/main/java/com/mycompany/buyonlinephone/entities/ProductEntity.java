/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;

import com.mycompany.buyonlinephone.enums.ProductStatus;
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
import javax.persistence.ManyToOne;
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
@Table(name = "product")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(nullable = false)
    private int numberGroup;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @NotBlank
    @Size(min = 3, max = 5000)
    private String description;
    
    private int quantity;

    private double price;
    
    private double  salePrice;

    @NotBlank
    private String color;

    @NotBlank
    private String memory;

    @NotBlank
    private String image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status ;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrdersDetailEntity> ordersDetails;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VoteAndCommentEntity> voteAndComments;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;   

    public ProductEntity() {
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getNumberGroup() {
        return numberGroup;
    }

    public void setNumberGroup(int numberGroup) {
        this.numberGroup = numberGroup;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Set<VoteAndCommentEntity> getVoteAndComments() {
        return voteAndComments;
    }

    public void setVoteAndComments(Set<VoteAndCommentEntity> voteAndComments) {
        this.voteAndComments = voteAndComments;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Set<OrdersDetailEntity> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(Set<OrdersDetailEntity> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }
    public int getCount () {
        int count = 0;
        for (VoteAndCommentEntity v : this.getVoteAndComments()) {
            count = count + 1;           
        }
        return count;
    }
    public float getVote () {
        float vote = 0;
        int count = 0;
        for (VoteAndCommentEntity v : this.getVoteAndComments()) {
            count = count + 1;
            vote = vote + v.getVote();
        }
        if (count == 0) {
            return 0;
        }
        return vote / count;
    }
    
   
}
