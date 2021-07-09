/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.enums.ProductStatus;
import com.mycompany.buyonlinephone.enums.PromotionStatus;
import com.mycompany.buyonlinephone.repository.ProductRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Set<ProductEntity> getProducts() {
        return productRepository.getProduct();
    }
    
     public Set<ProductEntity> getProductsByPrices(double min, double max) {
        return productRepository.getProductsByPrice(min, max);
    }

    public Set<ProductEntity> getProductsByCategory(
            String category) {
        return productRepository.getProductByCategory(category);
    }
     public Set<ProductEntity> getProductsByPrice(String category, double min, double max) {
        return productRepository.getProductByPrice(category, min, max);
    }

    public ProductEntity findProductById(int id) {
        return productRepository.findByProductById(id);
    }

    public boolean checkDiscount(String codeCount, String discount) {
        if (codeCount.equals(discount)) {
            return true;
        } else {
            return false;
        }
    }

    public Set<ProductEntity> findByNewProductInThirTyDay() {
        return productRepository.findByNewProductInThirTyDay();
    }

    public List<ProductEntity> searchName(String name) {
        return productRepository.findName(name);
    }

    public double price(double price, int quantity) {
        return price * quantity;
    }

    public boolean checkProductById(int id) {
        return productRepository.existsById(id);
    }

    public int getCountProduct() {
        return productRepository.countProduct();
    }
//    public int getCountProducts(String category, double min, double max) {
//        return productRepository.count(category, min, max);
//    }
    

    public ProductEntity findProductByNumberGroupAndColorAndMemoryAndProductId(int id,
            ProductStatus productStatus, String color, String memory) {
        return productRepository.findByProductByNumberGroupAndMemoryAndColorAndProductId(id,
                productStatus, color, memory);
    }

    public Set<ProductEntity> findProductByNumberGroupAndMemory(int numberGroup,
            ProductStatus productStatus, String memory) {
        return productRepository.findByProductByNumberGroupAndMemory(numberGroup,
                productStatus, memory);
    }

    public void save(ProductEntity product) {
        productRepository.save(product);
    }

    public Set<ProductEntity> getProductByNumberGroup(int id) {
        return productRepository.findByProductByNumberGroup(id);
    }

    public List<ProductEntity> getNumberGroupProduct(int id) {
        Set<String> memory = new HashSet<>();
        List<ProductEntity> toRemove = new ArrayList<>();
        for (ProductEntity product : this.getProductByNumberGroup(id)) {
            memory.add(product.getMemory());
        }
        for (String a : memory) {
            int count = 0;
            for (ProductEntity product : this.getProductByNumberGroup(id)) {
                if(a.equals(product.getMemory())) {
                    count++;
                    if (count == 1) {
                    toRemove.add(product);
                }
                }
                
            }
        }
        return toRemove;
    }

    public Set<ProductEntity> getProductInGroupAndMemory(Set<ProductEntity> product, ProductEntity entity) {
        Set<ProductEntity> color = new HashSet<>();
        for (ProductEntity productEntity : product) {
            if (productEntity.getMemory().equals(entity.getMemory())) {
                color.add(productEntity);
            }
        }
        return color;
    }

    public Set<String> getMemorys(Set<ProductEntity> product) {
        Set<String> memory = new HashSet<>();
        for (ProductEntity productEntity : product) {
            memory.add(productEntity.getMemory());
        }
        return memory;
    }

    public Set<String> getColors(Set<ProductEntity> product) {
        Set<String> color = new HashSet<>();
        for (ProductEntity productEntity : product) {
            color.add(productEntity.getColor());
        }
        return color;
    }

}
