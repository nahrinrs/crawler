package com.attune.crawler;

import com.attune.ddd.AppTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
       
    public void testApp2() throws Exception
    {
    	String[] args = new String[3];
    	args[0] = "https://bonobos.com/shop/new-arrivals";
    	args[1] = "https://bonobos.com/shop/tops";
    	args[2] = "https://bonobos.com/shop/tailored";
        App.main(args);
    }
}
