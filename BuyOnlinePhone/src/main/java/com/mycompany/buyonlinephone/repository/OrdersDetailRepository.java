/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.OrdersDetailEntity;
import com.mycompany.buyonlinephone.entities.ProductEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface OrdersDetailRepository extends CrudRepository<OrdersDetailEntity, Integer>{
    
    @Query(value = "Select od.product.id from OrdersDetailEntity od "
            + " group by od.product "
            + " order by sum(od.quantity) desc")
    List<Integer> getListProductId (Pageable pageable);
    
    @Query(value = "Select p from ProductEntity p "
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where p.id = ?1"
            )
    ProductEntity getOrderDetailById (int id);
}
