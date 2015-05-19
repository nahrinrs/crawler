package com.attune.crawler;

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
    	String[] args = new String[1];
    	args[0] = "https://bonobos.com/shop/new-arrivals";
    	//args[1] = "https://bonobos.com/shop/tops";
    	//args[2] = "https://bonobos.com/shop/tailored";
    	//args[3] = "https://bonobos.com/shop/bottoms";
    	//args[4] = "https://bonobos.com/shop/outerwear";
    	//args[5] = "https://bonobos.com/shop/maide-golf";
    	//args[6] = "https://bonobos.com/shop/accessories";
    	//args[7] = "https://bonobos.com/shop/shoes";
    	//args[8] = "https://bonobos.com/shop/maide-golf";

        App.main(args);
    }
}
