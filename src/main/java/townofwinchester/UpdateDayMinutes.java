package townofwinchester;

import java.util.TimerTask;

/*UpdateDayMinutes.java
 * 
 * The Timer Task that updates the minutes left in the day.
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class UpdateDayMinutes extends TimerTask
{
	private int minutes;
	
	/*
	 * A mutator method that allows the main timer class to tell
	 * this class the starting minutes value of the timer
	 * 
	 * @param The number of minutes the day timer starts with
	 */
	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}
	
	/*
	 * An accessor method that allows the minute integer 
	 * value to be accessed from the main timer class
	 * 
	 * @return the minute integer value
	 */
	public int getMinutes()
	{
		return minutes;
	}
	
	@Override
	public void run() 
	{
		minutes -=1;
	}
}
