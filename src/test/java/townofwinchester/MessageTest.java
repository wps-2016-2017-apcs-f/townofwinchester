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
 *
 * @author Jeremy Hughes
 * @author Ethan Wong
 */

public class MessageTest extends Message{
 public static void main (String[] args) {
  Message test = new Message();
  System.out.println(test.assignRole(false, "James "));
  System.out.println(test.compileText("James", "We should kill David"));
  test.parseMessage("CHAT:Tim:Oh no David died");
  System.out.println(test.text);
  System.out.println(test.name);
 }
}