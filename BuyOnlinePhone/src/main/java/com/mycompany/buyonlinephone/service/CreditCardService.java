/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.CreditCardEntity;
import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.repository.CreditCardRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CreditCardService {
    
    @Autowired
    public CreditCardRepository creditCardRepository;
        
    public CreditCardEntity findByCreditCardByCardNumberAndCvcCode (int cardNumber, String cvcCode) {
      return creditCardRepository.findByCardNumberLikeAndCvcCodeLike(cardNumber, cvcCode);
    }
   public CreditCardEntity findByCardNumber(int cardNumber) {
       return creditCardRepository.findByCardNumberLike(cardNumber);
   }
   
    public CreditCardEntity findByCvcCode(String cvcCode) {
       return creditCardRepository.findByCvcCodeLike(cvcCode);
   }
    public CreditCardEntity findById (int id) {
        Optional<CreditCardEntity> optional = creditCardRepository.findById(id);
        if(optional.isPresent()) {
           return optional.get();
        } else {
            return new CreditCardEntity();
        }        
    }
    public void save (CreditCardEntity creditCard) {
        creditCardRepository.save(creditCard);
    }
}
