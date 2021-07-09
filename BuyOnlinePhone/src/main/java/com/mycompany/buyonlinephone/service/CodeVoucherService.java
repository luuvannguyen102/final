/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.CodeVoucherEntity;
import com.mycompany.buyonlinephone.repository.CodeVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CodeVoucherService {
    
    @Autowired
    private CodeVoucherRepository codeVoucherRepository;
    
    public CodeVoucherEntity findCodeVoucherByName (String code) {
        return codeVoucherRepository.findCodeVoucherByName(code);
    }
    
    public void save (CodeVoucherEntity codeVoucher) {
        codeVoucherRepository.save(codeVoucher);
    }
}
