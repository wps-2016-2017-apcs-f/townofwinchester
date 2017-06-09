/*
 * CharacterTest.java
 */
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Character.java is the default class that all players are assigned to at the beginning. 
 * @author Jeremy Hudges
 * @author Ethan Wong
 */

public class CharacterTest extends TestCase{
	/**
     * This creates the test case
     *
     * @param testName name of the test case
     */
	public CharacterTest( String testName )
    	{
     	   super( testName );
   		}
   	/**
     * @return the suite of tests being tested
     */
   	public static Test suite()
    	{
        	return new TestSuite( CharacterTest.class );
    	}	

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

}