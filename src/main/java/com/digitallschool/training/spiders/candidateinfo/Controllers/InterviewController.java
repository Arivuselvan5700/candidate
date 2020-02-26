/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Controllers;

import com.digitallschool.training.spiders.candidateinfo.Entity.Interview;
import com.digitallschool.training.spiders.candidateinfo.Services.InterviewServices;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yesubabu
 */
@RestController
@RequestMapping("/test")

public class InterviewController {
    
@Autowired
    InterviewServices is;
    @GetMapping
    public String sayHello() {
        return "Hello World";
    }
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Interview> getInterviewList() throws SQLException {
        return is.getInterview();
    }
       @PostMapping(path = "/add")
    public ResponseEntity addInterview(@ModelAttribute("interview") Interview interview, BindingResult binding) {
        if (!binding.hasErrors() && is.addInterview(interview)) {
            return ResponseEntity.ok("Successfully Added");
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Incomplete or Invalid Data: "+binding);
        }
    }
    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteInterview(@ModelAttribute("interview") Interview interview,@RequestParam("candidate") int id, BindingResult binding) {
        if (!binding.hasErrors() && is.deleteInterview(id)) {
            return ResponseEntity.ok("Successfully Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Incomplete or Invalid Data: "+binding);
        }
    }
    @PutMapping
    public ResponseEntity updateInterview(@ModelAttribute("interview") Interview interview,@RequestParam("candidateId") int id, BindingResult binding) {
        if (!binding.hasErrors() && is.updateInterview(interview,id)) {
            return ResponseEntity.ok("Successfully Updated");
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Incomplete or Invalid Data: "+binding);
        }
    }
    @DeleteMapping("/deleteId")
    public String deleteInterviewForCandidate(@PathVariable("deleteId")long aadhar){
        if(is.deleteInterviewForCandidate(aadhar)){
            return "redirect:/interview";
        
        }else{
            return "Not deleted";
        }
    }
    @ModelAttribute("interview")
    public Interview setUpInterview() {
        return new Interview();
    }
}
