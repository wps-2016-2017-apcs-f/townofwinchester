/*
 * Messages.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;
import java.net.*;
import java.io.*;

/**
 * Messsages is the wrapper class for the messages that will be sent throughout the game
 *
 *
 * @author Jeremy Hughes
 * @author Ethan Wong
 *
 */
public class Messages
{  
 public String message;
 public Character god,sender,receiver;

 /** 
    * Describes the Message that is being Snet
    * 
    * @from args is used to indicate the character that is sending the message. 
    * 
    * @to args is used to indicate the character that will eventually receive the message and is able to access it. 
    *
    * @details describes the content of the message
    */
 public Messages(Character from, Character to, String details) {
  sender = from;
  receiver = to; 
  message = details; 
 }

}
