package com.richard.jobsitecrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        ArrayList<WebCrawler> bots = new ArrayList<>();
        bots.add(new WebCrawler("https://ng.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/linkedin-jobs?start=75", 1));
//        bots.add(new WebCrawler("https://www.vanguardngr.com/", 2));
//        bots.add(new WebCrawler("https://www.thisdaylive.com/", 3));

        for(WebCrawler w : bots) {
            try{
                w.getThread().join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }

//    https://ng.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/linkedin-jobs?start=5

}
