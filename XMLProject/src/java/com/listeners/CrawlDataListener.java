/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listeners;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Du
 */
public class CrawlDataListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("CrawlData listener has started!");

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new CrawlerTask(), 0, 5, TimeUnit.MINUTES);
    }

    class CrawlerTask implements Runnable {

        @Override
        public void run() {
//            Crawler crawler = new Crawler();
//            crawler.crawlUniversityData();
            Date date = new Date();
            System.out.println("Crawl done at " + date);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("CrawlData listener has been shut down!");
    }

}
