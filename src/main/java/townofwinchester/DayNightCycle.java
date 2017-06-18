package townofwinchester;

import java.util.TimerTask;

/*DayNightCycle.java
 * 
 * The Timer Task that allows the timer to end the day or night
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class DayNightCycle extends TimerTask 
{
	private boolean isDay = true;
	
	/*
	 * An accessor method that allows the day boolean value
	 * to be accessed from the main timer class
	 * 
	 * @return the day boolean value
	 */
	public boolean getIsDay()
	{
		return isDay;
	}
	
	@Override
	public void run() 
	{
		isDay = !isDay;
	}
}
