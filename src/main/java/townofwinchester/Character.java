/*
 * Character.java
 */
package townofwinchester;
//import org.apache.logging.log4j.*;
import java.net.*;
import java.io.*;
/**
 * Character.java is the default class that all players are assigned to at the beginning. 
 * @author Jeremy Hughes
 * @author Emily Lee
 * @author Samuel Lee
 * @author Lulu Tian
 * @author Ryan Tsai
 * @author Ethan Wong
 */
 
public class Character {
 public boolean mafiaStatus;
 public String name;
 
 public Character(String login){
  name = login; 
 }
 
 public void toMafia(){
  mafiaStatus = true;
 }
 
 public void toVillager(){
  mafiaStatus = false; 
 }
 
 public String getName(){
  return name;
 }

 public String returnMafia(){
 	return name; 
 }
 
 //public void setReady(){
  //Messages status = new Messages(name, "I'm ready to begin");
 //}
 
}
