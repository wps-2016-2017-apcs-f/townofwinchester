package townofwinchester;

import java.util.TimerTask;

/*UpdateDaySeconds.java
 * 
 * The Timer Task that updates the seconds left in the day.
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class UpdateDaySeconds extends TimerTask 
{
	private int seconds;
	
	/*
	 * A mutator method that allows the main timer class to tell
	 * this class the starting seconds value of the timer
	 * 
	 * @param The number of seconds the day timer starts with
	 */
	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}
	
	/*
	 * An accessor method that allows the second integer 
	 * value to be accessed from the main timer class
	 * 
	 * @return the second integer value
	 */
	public int getSeconds()
	{
		return seconds;
	}
	
	@Override
	public void run() 
	{
		seconds--;
	}
}
