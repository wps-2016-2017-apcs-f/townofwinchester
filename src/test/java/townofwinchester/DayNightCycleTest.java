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
public class DayNightCycleTest extends TestCase
{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DayNightCycleTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DayNightCycleTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
