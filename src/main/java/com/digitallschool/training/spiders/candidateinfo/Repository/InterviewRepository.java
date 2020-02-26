/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Repository;

import com.digitallschool.training.spiders.candidateinfo.Entity.Interview;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;         
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yesubabu
 */
@Repository
public class InterviewRepository {
    @Autowired
   DataSource ds;
   public List<Interview> allInterview(){
     List<Interview> interview = new ArrayList<>();
     try (Connection con = ds.getConnection();
                Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM interview")){
         while (rs.next()) {
                interview.add(
                        new Interview(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                 rs.getString(5),
                                 rs.getString(6),
                                 rs.getString(7))
                                  
                );
               }
     } catch (Exception e) {
            e.printStackTrace();
   }
         return interview;
   }
   public boolean addInterview(Interview interview) {
        try (Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement("INSERT INTO interview VALUES(?,?,?,?,?,?,?)")) {
            pst.setInt(1, interview.getCandidateId());
            pst.setString(2, interview.getModeOfInterview());
            pst.setString(3, interview.getLocation());
            pst.setString(4, interview.getRecommendation());
            pst.setString(5, interview.getInterviewPanel());
            pst.setString(6, interview.getReferences());
            pst.setString(7,interview.getInterviewDate());
                     pst.executeUpdate();
                    return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void deleteInterview(int id){
        try (Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM interview WHERE candidate=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean deleteInterviewForACandidate(long aadhar){
        try(Connection con=ds.getConnection();
                    PreparedStatement pst=con.prepareStatement("DELETE FROM interviewOne WHERE candidate=?");
                    ){
                pst.setLong(1, aadhar);
                if(pst.executeUpdate()!=0){
                    return true;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return false;
    }
    public void updateInterview(Interview interview,int id) {
        try (Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement("UPDATE interview SET mode_of_interview=?, location=?, recommendation=?, interview_panel=?, reference=?, interview_date=? WHERE candidate=?")) {
            pst.setInt(1, id);
            
            pst.setString(2, interview.getInterviewDate());
            pst.setString(3, interview.getRecommendation());
            pst.setString(4, interview.getLocation());
            pst.setString(5, interview.getReferences());
            pst.setString(6, interview.getModeOfInterview());
            pst.setString(7,interview.getInterviewDate());
       pst.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

