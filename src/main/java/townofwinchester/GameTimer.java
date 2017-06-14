
package townofwinchester;
import java.util.*;

/*GameTimer.java
 * 
 * The in-game timer that dictates the day night cycle and the discussion periods
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */

public class GameTimer 
{
  private Timer gameTimer;
  private DayNightCycle dayNightCycle = new DayNightCycle();
  private DiscussionControl discussionControl = new DiscussionControl();
  
  /*
   * An accessor method that allows other programs to access whether it is daytime or not.
   * 
   * @return The boolean value of day vs. night
   */
  public boolean getIsDay()
  {
    return dayNightCycle.getIsDay();
  }
  
  /*
   * An accessor method that allows other programs to access whether the players are allowed to talk or not.
   * 
   * @return The boolean value of whether the players are allowed to discuss or not.
   */
  public boolean getIsDiscussion()
  {
	  return discussionControl.getIsDiscussion();
  }
  
  /*
   * Calling this method starts a 1-minute countdown. At the end of the countdown, the day boolean value switches
   * to its opposite.
   */
  public void startDayCountdown()
  {
	  gameTimer.schedule(dayNightCycle, 60000); //1 minute for first day
  }
  
  /*
   * Calling this method starts a 30-second countdown. At the end of the countdown, the discussable 
   * boolean value switches to its opposite
   */
  public void startDiscussionCountdown()
  {
	  gameTimer.schedule(discussionControl, 30000); //30 second discussion period
  }

}
  
      
    
  
  
  

