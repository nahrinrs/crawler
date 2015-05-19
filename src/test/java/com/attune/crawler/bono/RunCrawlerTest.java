package com.attune.crawler.bono;

import com.attune.crawler.bono.RunCrawler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class RunCrawlerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RunCrawlerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RunCrawlerTest.class );
    }
    
    
    public void testEmptyUrl() throws Exception{
    	RunCrawler rc = new RunCrawler();
    	assertFalse(rc.Run());
    	
    }
    
    public void testOtherSite() throws Exception{
    	String[] args = new String[1];
    	args[0] = "http://www.bluefly.com/new_arrivals?cpath=designer-womens&so=new&sosc=true";
    	RunCrawler rc = new RunCrawler();
    	rc.setCategories(args);
    	assertTrue(rc.Run());
    }
    
    public void testNewArrivals() throws Exception
    {
    	String[] args = new String[1];
    	args[0] = "https://bonobos.com/shop/new-arrivals";
    	RunCrawler rc = new RunCrawler();
    	rc.setCategories(args);
    	assertTrue(rc.Run());
    }
 /*
    public void testTopsBottoms() throws Exception{
    	String[] args = new String[2];
    	args[0] = "https://bonobos.com/shop/tops";
    	args[1] = "https://bonobos.com/shop/bottoms";
    	RunCrawler rc = new RunCrawler();
    	rc.setCategories(args);
    	assertTrue(rc.Run());
    }
    
    public void testTailored() throws Exception{
    	String[] args = new String[1];
    	args[0] = "https://bonobos.com/shop/tailored";
    	RunCrawler rc = new RunCrawler();
    	rc.setCategories(args);
    	assertTrue(rc.Run());
    }
    
    public void testRest() throws Exception{
    	String[] args = new String[5];
    	args[0] = "https://bonobos.com/shop/outerwear";
    	args[1] = "https://bonobos.com/shop/maide-golf";
    	args[2] = "https://bonobos.com/shop/accessories";
    	args[3] = "https://bonobos.com/shop/shoes";
    	args[4] = "https://bonobos.com/shop/maide-golf";

    	RunCrawler rc = new RunCrawler();
    	rc.setCategories(args);
    	assertTrue(rc.Run());
    }
    */
}
