package townofwinchester;

import java.util.TimerTask;

/*GameTimer.java
 * 
 * The Timer Task that dictates whether the players can discuss or not
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */
public class DiscussionControl extends TimerTask
{
	private boolean isDiscussion = false;
	
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
