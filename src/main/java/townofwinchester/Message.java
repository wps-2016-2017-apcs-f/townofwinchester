/*
 * Message.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;
import java.net.*;
import java.io.*;
 
/**
 * Messsage is the wrapper class for the messages that will be sent throughout the game
 *
 *
 * @author Jeremy Hughes
 * @author Ethan Wong
 *
 */
public class Message {  
 public String message;
 public static enum Type {CHAT, JOIN, MVOTE, VOTE};
 //public Character god,sender,receiver;
 
 /** 
    * Describes the Message that is being Snet
    * 
    * @from args is used to indicate the character that is sending the message. 
    * 
    * @to args is used to indicate the character that will eventually receive the message and is able to access it. 
    *
    * @details describes the content of the message
    */
 //public Message(Character from, Character to, String details) {
 // sender = from;
 // receiver = to; 
 // message = details; 
 //}

 public String assignRole(boolean mafiaStatus, String name) {
  //Character character = new Character();
  //String name1 = character.getName();
  if (mafiaStatus == true) 
   return "You are a Mafia";
  else   
   return name + "is a villager";
 }
 
 public String compileText(String name, String message) {
   return Type.CHAT + ":" + name + ":" + message; 
 }
}