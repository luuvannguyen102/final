/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.repository;

import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.enums.ProductStatus;
import com.mycompany.buyonlinephone.enums.PromotionStatus;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query(value = "Select p from ProductEntity p"
            + " LEFT JOIN FETCH p.voteAndComments"
            + " order by p.price asc"
    )
    Set<ProductEntity> getProduct();

    @Query(value = "Select p from ProductEntity p"
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where  p.category.name Like ?1")
    Set<ProductEntity> getProductByCategory(String category);

    @Query(value = "Select p from ProductEntity p "
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where DATEDIFF(dd,p.createDate, GETDATE()) <= 30 ")
    Set<ProductEntity> findByNewProductInThirTyDay();

    @Query(value = "Select p from ProductEntity p "
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where p.id Like ?1"
    )
    ProductEntity findByProductById(int id);

    @Query(value = "Select p from ProductEntity p"
            + " where p.numberGroup in (Select p.numberGroup from ProductEntity p where p.id Like ?1)"
            + " and p.status Like ?2"
            + " and p.color Like ?3"
            + " and p.memory Like ?4"
    )
    ProductEntity findByProductByNumberGroupAndMemoryAndColorAndProductId(int id,
            ProductStatus productStatus, String color, String memory);

    @Query(value = "Select p from ProductEntity p "
            + " where p.numberGroup in "
            + "(Select p.numberGroup from ProductEntity p where p.id Like ?1)"
    )
    Set<ProductEntity> findByProductByNumberGroup(int id);

    @Query(value = "Select p from ProductEntity p"
            + " where p.numberGroup Like ?1"
            + " and p.status Like ?2"
            + " and p.memory Like ?3"
    )
    Set<ProductEntity> findByProductByNumberGroupAndMemory(int numberGroup,
            ProductStatus productStatus, String memory);

    @Query("Select p from ProductEntity p"
            + " LEFT JOIN FETCH p.voteAndComments "
            + " where p.name Like ?1%")
    List<ProductEntity> findName(String name);

    @Query(value = "Select count(p) from ProductEntity p")
    int countProduct();
    
//     @Query(value = "Select count(p) from ProductEntity p"
//            + " LEFT JOIN FETCH p.voteAndComments"
//            + " where  p.category.name Like ?1"
//            + " and p.salePrice > ?2"
//            + " and p.salePrice < ?3")
//    int count(String category, double min, double max);
//    
    @Query(value = "Select p from ProductEntity p"
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where  p.category.name Like ?1"
            + " and p.salePrice > ?2"
            + " and p.salePrice < ?3")
    Set<ProductEntity> getProductByPrice(String category, double min, double max);
    
     @Query(value = "Select p from ProductEntity p"
            + " LEFT JOIN FETCH p.voteAndComments"
            + " where p.salePrice > ?1"
            + " and p.salePrice < ?2")
    Set<ProductEntity> getProductsByPrice( double min, double max);
 

}
