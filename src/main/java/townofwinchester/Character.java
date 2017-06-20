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
 * @author Roy Xing
 */
 
public class Character {
 private boolean mafiaStatus;
 private String name;
 private int ID;
 
 public Character(String login, int ID){
	name = login;
	this.ID = ID;
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
 
 public int getID(){
	return ID;
 }

 public String returnMafia(){
 	return name; 
 }
 
}