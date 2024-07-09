package com.richard.jobsitecrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

public class WebCrawler implements Runnable{

    private static final int MAX_DEPTH = 3;
    private Thread thread;
    private String first_link;
    private ArrayList<String> visitedLinks = new ArrayList<String>();
    private int ID;
    public WebCrawler(String link, int num){
        System.out.println("Webcrawler created");
        first_link = link;
        ID = num;

        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        crawl(1, first_link);
    }

    private void crawl(int level, String url) {
        if(level <= MAX_DEPTH){
            Document doc = request(url);
            if(doc != null){
                Elements li = doc.select("li");
                int i = 1;
                for(Element item:li){
                     String jobTitle = item.select("h3").text();
                     String jobUrl = item.select(".base-card__full-link").attr("href");
                     String jobListed = item.select("time").attr("text");

                     String companyName = item.select("h4 a").text();
                     String companyLink = item.select("h4 a").attr("href");
                     String companyLocation = item.select(".job-search-card_location").text();


                    System.out.println("jobTitle " + jobTitle);
                    System.out.println("jobUrl " + jobUrl);
                    System.out.println("companyName " + companyName);
                    System.out.println("companyLink " + companyLink);
                    System.out.println("companyLocation " + companyLocation);

                    i++;
                }

            }
        }
    }
    private Document request(String url){
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode() == 200){
                Element first_job_on_page = doc.body();
                visitedLinks.add(url);

                return doc;
            }
            return null;
        } catch (IOException e){
            return null;
        }
    }
    public Thread getThread(){
        return thread;
    }
}
