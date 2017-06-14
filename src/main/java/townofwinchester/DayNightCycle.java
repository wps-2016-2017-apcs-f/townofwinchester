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
