/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Repository;

import com.digitallschool.training.spiders.candidateinfo.Entity.Candidate;
import com.digitallschool.training.spiders.candidateinfo.Services.InterviewServices;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yesubabu
 */
@Repository
public class CandidateRepository {
    
    @Autowired 
            InterviewServices interviewServices;
@Autowired MongoDbFactory mf;
   public Candidate getCandidateForInterview(long aadhar){
       Candidate temp=null;
        //Consumer<Candidate> candid=e->e;
       CodecRegistry codec=CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       MongoCollection collection=mf.getDb("komali").getCollection("candidate", Candidate.class)
		.withCodecRegistry(codec);
        FindIterable<Candidate> iter=collection.find(eq("aadhar",aadhar),Candidate.class);
        for(Candidate c:iter){
            temp=c;
        }
       return temp;
   }
   public boolean updateCandidate(long aadharNo, Candidate candidate) {
        try {
            CodecRegistry codec = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
          MongoCollection collection=  mf.getDb("komali").getCollection("candidate", Candidate.class).withCodecRegistry(codec);
           collection.replaceOne(eq("aadhar", aadharNo), candidate);
           return true;
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return false;
    }
   public boolean deleteCandidate(long aadharNo){
       CodecRegistry codec=CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       MongoCollection collection=mf.getDb("komali").getCollection("candidate", Candidate.class)
		.withCodecRegistry(codec);
                collection.deleteOne(eq("aadhar",aadharNo));
                interviewServices.deleteInterviewForCandidate(aadharNo);
       return true;
   }
   public boolean addCandidate(Candidate candidate){
       CodecRegistry codec=CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       MongoCollection collection=mf.getDb("komali").getCollection("candidate", Candidate.class)
		.withCodecRegistry(codec);
       collection.insertOne(candidate);
       return true;
   }
   public List<Candidate> allCandidates(){
       List<Candidate> candidates=new ArrayList();
        CodecRegistry codec=CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        Consumer<Candidate> candid=e->candidates.add(e);
       mf.getDb("komali").getCollection("candidate", Candidate.class)
		.withCodecRegistry(codec)
		.find().forEach(candid);
        return candidates;
   }

}






    

