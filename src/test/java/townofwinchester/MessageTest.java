/*
 * MessageTest.java
 */
package townofwinchester;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * MesssageTest is the test class for Message.java
 *
 * @author Jeremy Hughes
 * @author Ethan Wong
 */


public class MessageTest extends TestCase{
 /**
     * This creates the test case
     *
     * @param testName name of the test case
     */
 public MessageTest( String testName )
     {
         super( testName );
     }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
     {
         return new TestSuite(MessageTest.class );
     } 

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

}
/**public class MessageTest extends TestCase {
    public static void main (String[] args) {
        Message test = new Message("CHAT:Tim:Oh no David died");
        System.out.println(test.assignRole(false, "James "));
        System.out.println(test.compileText("James", "We should kill David"));
        System.out.println(test.getType());
        System.out.println(test.getText());
        System.out.println(test.getName());
    }
*/    
