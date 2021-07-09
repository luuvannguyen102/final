/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.OrdersEntity;
import com.mycompany.buyonlinephone.enums.OrderStatus;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface OrderRepository extends CrudRepository<OrdersEntity, Integer>{
    
    
    List<OrdersEntity> findByOrderStatusLike (OrderStatus orderStatus);
    
    @Query(value = "Select o from OrdersEntity o"
            + " JOIN FETCH o.ordersDetails"
            + " where o.code = ?1")  
    OrdersEntity findOrderByCode (String code);
    
    @Query(value = "Select o from OrdersEntity o"
            + " JOIN FETCH o.ordersDetails"
            + " where o.id = ?1")
    OrdersEntity findOrderByIdQuery(int id);
}
