/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.VoteAndCommentEntity;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.buyonlinephone.repository.VoteAndCommentRepository;

/**
 *
 * @author Admin
 */
@Service
public class VoteAndCommentService {
    
    @Autowired
    private VoteAndCommentRepository voteAndCommentRepository;
    
//    public float totalVoteProduct (Set<VoteEntity> voteEntitys) {
//        int count = 0;
//        float votes = 0;
//        for(VoteEntity vote : voteEntitys) {
//            count = count + 1;
//            votes = votes + vote.getVote();
//        }
//        if(count > 0 && votes > 0) {
//            return votes/count;
//        } else {
//            return 0;
//        }
//        
//    }
//    
//    public float countVoteProduct (Set<VoteEntity> voteEntitys) {
//        int count = 0;       
//        for(VoteEntity vote : voteEntitys) {
//            count = count + 1;           
//        }
//        if(count > 0 ) {
//            return count;
//        } else {
//            return 0;
//        }
//        
//    }
    
    public void save (VoteAndCommentEntity voteAndCommentEntity) {
        voteAndCommentRepository.save(voteAndCommentEntity);
    }
}
