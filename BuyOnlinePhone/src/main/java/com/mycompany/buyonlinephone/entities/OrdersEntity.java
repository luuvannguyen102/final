/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.entities;

import com.mycompany.buyonlinephone.enums.Gender;
import com.mycompany.buyonlinephone.enums.OrderStatus;
import com.mycompany.buyonlinephone.enums.PaymentStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "orders")
public class OrdersEntity extends Personal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    private String code = randomAlphaNumeric(10);

    private String note;   
    
    private int discount = 0;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrdersDetailEntity> ordersDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentEntity payment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public OrdersEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<OrdersDetailEntity> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(Set<OrdersDetailEntity> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
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

    public int randomNumber(int min, int max) {
        Random generator = new Random();
        return generator.nextInt((max - min) + 1) + min;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrdersDetailEntity orderDetail : this.getOrdersDetails()) {
            totalPrice = totalPrice + orderDetail.getAmount();
        }        
        return totalPrice;
    }

    public int getCountOrdersDetailInOrders() {
        int count = 0;
        for (OrdersDetailEntity orderDetail : this.getOrdersDetails()) {
            count = count + 1;
        }
        return count;
    }

}
