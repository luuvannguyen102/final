/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.PaymentEntity;
import com.mycompany.buyonlinephone.repository.PaymentRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PaymentService {
    
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public void save (Set<PaymentEntity> paymentEntity) {
        paymentRepository.saveAll(paymentEntity);
    }
    
    public void save (PaymentEntity paymentEntity) {
        paymentRepository.save(paymentEntity);
    }
    
}
