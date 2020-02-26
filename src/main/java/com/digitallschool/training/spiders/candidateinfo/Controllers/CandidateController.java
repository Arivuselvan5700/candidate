/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallschool.training.spiders.candidateinfo.Controllers;

import com.digitallschool.training.spiders.candidateinfo.Entity.Candidate;
import com.digitallschool.training.spiders.candidateinfo.Services.CandidateService;
import com.digitallschool.training.spiders.candidateinfo.Services.InterviewServices;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author yesubabu
 */
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;
    @Autowired
    InterviewServices interviewservice;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Candidate> viewCandidate() {
        return candidateService.getCandidates();
    }

    public static String uploadDirectory = System.getProperty("C:\\Users\\yesubabu\\Desktop\\check") + "/uploads";

    @RequestMapping("/")
    public String UploadPage(Model model) {
        return "uploadview";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile file) {
        StringBuilder fileNames = new StringBuilder();

        System.out.println("check" + file);
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename() + "");

        try {

            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString());
        return "uploadstatusview";
    }

    /*  private static String UPLOADED_FOLDER = "F://temp//";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addCandidate(@ModelAttribute Candidate candidate) {
        candidateService.addCandidate(candidate);
    }

    @DeleteMapping
    public void deleteCandidate(@RequestParam long aadhar) {
        
        candidateService.deleteCandidate(aadhar);
    }

    @PutMapping
    public ResponseEntity<String> updateCandidate(@RequestParam long aadharNo, @ModelAttribute Candidate candidate) {
        if (candidateService.updateCandidate(aadharNo, candidate)) {
            return new ResponseEntity("candidate is updated sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Candidate is not updated", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ModelAttribute("candid")
    public Candidate getCandid() {
        return new Candidate();
    }
}
