package townofwinchester;

import java.util.TimerTask;

/*DiscussionControl.java
 * 
 * The Timer Task that dictates whether the players can discuss or not
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class DiscussionControl extends TimerTask
{
	private boolean isDiscussion = false;
	
	/*
	 * An accessor method that allows the discussable
	 * boolean valueto be accessed from the main timer class
	 * 
	 * @return the discussable boolean value
	 */
	public boolean getIsDiscussion()
	{
		return isDiscussion;
	}

	@Override
	public void run() 
	{
		isDiscussion = !isDiscussion;
	}
}
