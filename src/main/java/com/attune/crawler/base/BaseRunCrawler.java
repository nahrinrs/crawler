package com.attune.crawler.base;

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
public abstract class BaseRunCrawler 
{
	public static String OUTPATH = "/data/crawl/output";
	public static PrintWriter visitlog = null, fetchlog = null;
	protected static String crawlStorageFolder = "/data/crawl/root";
	private String[] categories = null;
	protected CrawlConfig config = null;
	private int numberOfCrawlers = 10;
	private int maxDepthOfCrawling = 1;
	private int delay = 500;
	private int maxPagesToFetch = 500;
   
	public int getNumberOfCrawlers() {
		return numberOfCrawlers;
	}
	public void setNumberOfCrawlers(int numberOfCrawlers) {
		this.numberOfCrawlers = numberOfCrawlers;
	}
	public int getMaxDepthOfCrawling() {
		return maxDepthOfCrawling;
	}
	public void setMaxDepthOfCrawling(int maxDepthOfCrawling) {
		this.maxDepthOfCrawling = maxDepthOfCrawling;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public int getMaxPagesToFetch() {
		return maxPagesToFetch;
	}
	public void setMaxPagesToFetch(int maxPagesToFetch) {
		this.maxPagesToFetch = maxPagesToFetch;
	}
	public String[] getCategories() {
		return this.categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
	public BaseRunCrawler() throws IOException{
		 File outpath_f = new File(OUTPATH);
         if (!outpath_f.exists()) 
        	 outpath_f.mkdirs();
		 visitlog = new PrintWriter(new BufferedWriter(new FileWriter(OUTPATH+"/"+"visit.csv", true)));
		 fetchlog = new PrintWriter(new BufferedWriter(new FileWriter(OUTPATH+"/"+"fetch.csv", true)));

         config = new CrawlConfig();
         config.setCrawlStorageFolder(crawlStorageFolder);
         config.setMaxDepthOfCrawling(maxDepthOfCrawling);
         config.setPolitenessDelay(delay);
         config.setMaxPagesToFetch(maxPagesToFetch);
	}
	public abstract boolean Run() throws Exception;
	
}
