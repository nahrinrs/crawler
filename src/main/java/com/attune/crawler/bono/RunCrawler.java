package com.attune.crawler.bono;

import java.io.*;

import com.attune.crawler.base.BaseRunCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Hello world!
 *
 */
public class RunCrawler extends BaseRunCrawler{
	
	public RunCrawler() throws IOException{
		super();
	}

	public boolean Run() throws Exception {

	    	 if (this.getCategories() == null || this.getCategories().length == 0 ) return false;

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
	         for (int i=0; i<this.getCategories().length; i++){
	        	 controller.addSeed(this.getCategories()[i]);
	         }
	         /*
	          * Start the crawl. This is a blocking operation, meaning that your code
	          * will reach the line after this only when crawling is finished.
	          */
	         controller.start(Crawler.class, this.getNumberOfCrawlers());
	         /*
			 * Output cleanup
			 */
			 visitlog.close();
			 fetchlog.close();
			 return true;
	    }

	
}
