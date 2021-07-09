/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.PromotionEntity;
import com.mycompany.buyonlinephone.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PromotionService {
    
    @Autowired
    private PromotionRepository promtionRepository;
    
    public PromotionEntity findPromotionByCodeVoucher () {
        return promtionRepository.findPromotionByCodeVoucher();
    }
}
