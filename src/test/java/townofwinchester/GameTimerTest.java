/**
 * 
 */
package townofwinchester;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * @author Tim Dalton
 * @author Adrian Stone
 *
 */
public class GameTimerTest extends TestCase
{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GameTimerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GameTimerTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
