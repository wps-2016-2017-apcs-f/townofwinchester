package townofwinchester;

import java.util.TimerTask;

/*UpdateDiscussionSeconds.java
 * 
 * The Timer Task that updates the seconds left in the discussion period.
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class UpdateDiscussionSeconds extends TimerTask
{
	private int seconds;
	
	/*
	 * A mutator method that allows the main timer class to tell
	 * this class the starting minutes value of the timer
	 * 
	 * @param The number of seconds the discussion timer starts with
	 */
	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}
	
	/*
	 * An accessor method that allows the discussion length
	 * integer value to be accessed from the main timer class
	 * 
	 * @return the discussion second integer value
	 */
	public int getSeconds()
	{
		return seconds;
	}
	
	@Override
	public void run() 
	{
		seconds -=1;
	}
}
