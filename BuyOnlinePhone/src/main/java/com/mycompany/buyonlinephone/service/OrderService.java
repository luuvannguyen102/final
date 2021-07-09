/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.OrdersEntity;
import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.enums.OrderStatus;
import com.mycompany.buyonlinephone.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class OrderService {
    
    
    @Autowired
    private OrderRepository orderRepository;
    
    public void save(OrdersEntity orders) {
        orderRepository.save(orders);
    } 
    
   public OrdersEntity findOrderById (int id) {
        Optional<OrdersEntity> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return new OrdersEntity();
        }
    }
   
   public OrdersEntity findOrderByIdQuery (int id) {
        return orderRepository.findOrderByIdQuery(id);
    }
   
   public List<OrdersEntity> findByStatusLike(OrderStatus orderStatus) {
      return orderRepository.findByOrderStatusLike(orderStatus);
   }
   
   public OrdersEntity findOrdersByCode (String code) {
       return orderRepository.findOrderByCode(code);
   }
    
   
}
