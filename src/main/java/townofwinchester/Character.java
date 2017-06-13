/*
 * Character.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;
import java.net.*;
import java.io.*;
/**
 * Character.java is the default class that all players are assigned to at the beginning. 
 * @author Jeremy Hudges
 * @author Emily Lee
 * @author Samuel Lee
 * @author Lulu Tian
 * @author Ryan Tsai
 * @author Ethan Wong
 */

public class Character{
 public boolean MafiaStatus;
 private String name;

 public Character(String name){
   this.name = name;
 }
 
 public void toMafia(){
  MafiaStatus = true;
 }

 public void toVillager(){
  MafiaStatus = false; 
 }
}