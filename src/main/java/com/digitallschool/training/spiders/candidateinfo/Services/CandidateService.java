/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Services;

import com.digitallschool.training.spiders.candidateinfo.Entity.Candidate;
import com.digitallschool.training.spiders.candidateinfo.Repository.CandidateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yesubabu
 */
@Service
public class CandidateService {
    

	     @Autowired CandidateRepository candidaterepo;
    public boolean addCandidate(Candidate candidate){
        return candidaterepo.addCandidate(candidate);
    }
    public List<Candidate> getCandidates(){
        return candidaterepo.allCandidates();
    }
    public boolean deleteCandidate(long aadharNo){
        
        return candidaterepo.deleteCandidate(aadharNo);
    }
    public boolean updateCandidate(long aadharNo,Candidate candidate){
        return candidaterepo.updateCandidate(aadharNo, candidate);
    }
    public Candidate findCandidate(long aadhar){
        return candidaterepo.getCandidateForInterview(aadhar);
    }
   
}