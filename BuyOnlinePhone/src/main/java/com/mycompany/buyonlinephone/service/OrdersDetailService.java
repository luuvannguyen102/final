/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.OrdersDetailEntity;
import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.repository.OrdersDetailRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class OrdersDetailService {
    
    @Autowired
    private OrdersDetailRepository ordersDetailRepository;
    
    public List<Integer> getListProductId (Pageable pageable) {
        return ordersDetailRepository.getListProductId(pageable);
    }
    
    public List<ProductEntity> getTopSelling (Pageable pageable) {
        List<ProductEntity> p = new ArrayList<>();
        for(Integer id : getListProductId(pageable)) {
           p.add(ordersDetailRepository.getOrderDetailById(id)) ;
        }
        return p;
    }
    
    
    public void save (Set<OrdersDetailEntity> ordersDetails) {
        ordersDetailRepository.saveAll(ordersDetails);
    }
    
    public void delete (int id) {
        ordersDetailRepository.deleteById(id);
        
    }
    
    public boolean exist (int id) {
        return ordersDetailRepository.existsById(id);
    }
}
