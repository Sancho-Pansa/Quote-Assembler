package com.fandom.leagueoflegends.ru.QuoteAssembly;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	private VoiceLineFormatter vlf;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        vlf = new VoiceLineFormatter();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String result = "";
    	try {
			vlf.openCSV("Афелий Классический.csv");
			result = vlf.formatCSV();
			Writer fwriter = new FileWriter("АфелийФразы.txt");
			BufferedWriter bw = new BufferedWriter(fwriter);
			bw.write(result);
			bw.flush();
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Could not write result");
			e.printStackTrace();
		} 
        assertTrue( result.length() > 0 );
    }
}
