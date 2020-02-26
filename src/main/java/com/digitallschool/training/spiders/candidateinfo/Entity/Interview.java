/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Entity;

/**
 *
 * @author yesubabu
 */
public class Interview {
    
  private int candidateId;
  
  private String modeOfInterview;
  private String interviewDate;
  private String location;
  private String interviewPanel;
  private String recommendation;
  private String references;
  public Interview(){
      super();
  }
public Interview(int candidateId, String
modeOfInterview, String interviewDate, String location, String recommendation,
String interviewPanel, String references) {
        this.candidateId = candidateId;
       
        this.modeOfInterview = modeOfInterview;
        this.interviewDate = interviewDate;
        this.location = location;
        this.recommendation = recommendation;
        this.interviewPanel = interviewPanel;
        this.references = references;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getModeOfInterview() {
        return modeOfInterview;
    }

    public void setModeOfInterview(String modeOfInterview) {
        this.modeOfInterview = modeOfInterview;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterviewPanel() {
        return interviewPanel;
    }

    public void setInterviewPanel(String interviewPanel) {
        this.interviewPanel = interviewPanel;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

}
   