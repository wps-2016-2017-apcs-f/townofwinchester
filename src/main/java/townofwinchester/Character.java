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
 * @author Ethan Wong
 */

public class Character{
	public boolean MafiaStatus;

	public Character(){

	}
	
	public void toMafia(){
		MafiaStatus = true;
	}

	public void toVillager(){
		MafiaStatus = false; 
	}
}