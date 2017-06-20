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

public class MessageTest extends TestCase {
    public static void main (String[] args) {
        Message test = new Message("CHAT:Tim:Oh no David died");
        System.out.println(test.assignRole(false, "James "));
        System.out.println(test.compileText("James", "We should kill David"));
        System.out.println(test.getType());
        System.out.println(test.getText());
        System.out.println(test.getName());
    }
}