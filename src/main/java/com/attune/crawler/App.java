package com.attune.crawler;

import java.io.*;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String OUTPATH = "/data/crawl/output";
	public static PrintWriter visitlog = null, fetchlog = null;
	private static String crawlStorageFolder = "/data/crawl/root";
	
    public static void main( String[] args ) throws Exception
    {
    	 if (args.length == 0 ) return;
    	 File outpath_f = new File(OUTPATH);
         if (!outpath_f.exists()) 
        	 outpath_f.mkdirs();
		 visitlog = new PrintWriter(new BufferedWriter(new FileWriter(OUTPATH+"/"+"visit.csv")));
		 fetchlog = new PrintWriter(new BufferedWriter(new FileWriter(OUTPATH+"/"+"fetch.csv")));

    	 
         int numberOfCrawlers = 7;

         CrawlConfig config = new CrawlConfig();
         config.setCrawlStorageFolder(crawlStorageFolder);
         config.setMaxDepthOfCrawling(1);
         config.setPolitenessDelay(500);
         config.setMaxPagesToFetch(500);

         /*
          * Instantiate the controller for this crawl.
          */
         PageFetcher pageFetcher = new PageFetcher(config);
         RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
         RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
         CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

         /*
          * For each crawl, you need to add some seed urls. These are the first
          * URLs that are fetched and then the crawler starts following links
          * which are found in these pages
          */
         for (int i=0; i<args.length; i++){
        	 controller.addSeed(args[i]);
         }
         /*
          * Start the crawl. This is a blocking operation, meaning that your code
          * will reach the line after this only when crawling is finished.
          */
         controller.start(Crawler.class, numberOfCrawlers);
         /*
		 * Output cleanup
		 */
		 visitlog.close();
		 fetchlog.close();
    }
}
