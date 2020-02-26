package com.digitallschool.training.spiders;

import com.digitallschool.training.spiders.candidateinfo.Controllers.CandidateController;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.digitallschool.training.spiders","candidateinfo"})

public class CandidateInfoApplication {

	public static void main(String[] args) {
       //     new File(CandidateController.uploadDirectory).mkdir();
		SpringApplication.run(CandidateInfoApplication.class, args);
	}

}

