/*
 * MessagesTest.java
 */
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * MesssagesTest is the test class for Messages.java
 *
 *
 * @author Jeremy Hudges
 * @author Ethan Wong
 */

public class MessagesTest extends TestCase{
	/**
     * This creates the test case
     *
     * @param testName name of the test case
     */
	public Messages( String testName )
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