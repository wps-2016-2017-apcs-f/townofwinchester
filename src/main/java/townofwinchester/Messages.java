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
 * @author Jeremy Hudges
 * @author Ethan Wong
 */

public class Messages
{  
	private boolean DayStatus, NightStatus;

	public Message(){

	}

	public getHeader(){

	}

	public void toDay(){
		NightStatus = false;
		DayStatus = true;
	}

	public void toNight(){
		DayStatus = false;
		NightStatus = true;
	}

	public getTime(){
		if DayStatus = true
			logger.info("It's Day");
		else
			logger.info("It's Night");

	}

}

