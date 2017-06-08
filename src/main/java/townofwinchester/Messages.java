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

	public String Message(){

	}

	public String Header(){

	}

	public void Day(){
		NightStatus = false;
		DayStatus = true;
	}

	public void Night(){
		DayStatus = false;
		NightStatus = true;
	}

	public Time(){
		if DayStatus = true
			logger.info("It's Day");
		else
			logger.info("It's Night");

	}

}

