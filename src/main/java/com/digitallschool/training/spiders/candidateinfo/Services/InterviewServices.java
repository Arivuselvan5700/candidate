/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Services;


import com.digitallschool.training.spiders.candidateinfo.Entity.Interview;
import com.digitallschool.training.spiders.candidateinfo.Repository.InterviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yesubabu
 */
@Service
public class InterviewServices {
    @Autowired
    InterviewRepository ir;
    public List<Interview> getInterview() {
        return ir.allInterview();
    }
    public boolean addInterview(Interview interview){
        if(ir.addInterview(interview)){
            return true;
        }
        return false;
    }
    public boolean deleteInterview(int id) {
        ir.deleteInterview(id);
        return true;
    }
    public boolean deleteInterviewForCandidate(long aadhar){
        return ir.deleteInterviewForACandidate(aadhar);
    }
    public boolean updateInterview(Interview interview, int id) {
        ir.updateInterview(interview,id);
        return true;
    }
}

