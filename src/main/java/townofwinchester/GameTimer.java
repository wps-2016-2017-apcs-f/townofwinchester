
package townofwinchester;
import java.util.Timer;

/*GameTimer.java
 * 
 * The main timer class that controls the day-night cycle and the discussion length.
 * This class is designed to be instantiated from the MyFrame class.
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */

public class GameTimer 
{
  private Timer gameTimer = new Timer();
  private DayNightCycle dayNightCycle = new DayNightCycle();
  private DiscussionControl discussionControl = new DiscussionControl();
  private UpdateDayMinutes updateDayMinutes = new UpdateDayMinutes();
  private UpdateDaySeconds updateDaySeconds = new UpdateDaySeconds();
  private UpdateDiscussionSeconds updateDiscussionSeconds = new UpdateDiscussionSeconds();
  
  /*
   * An accessor method that allows other classes to access whether 
   * it is daytime or not.
   * 
   * @return The boolean value of day vs. night
   */
  public boolean getIsDay()
  {
    return dayNightCycle.getIsDay();
  }
  
  /*
   * An accessor method that allows other classes to access whether 
   * the players are allowed to talk or not.
   * 
   * @return The boolean value of whether the players are allowed to discuss or not.
   */
  public boolean getIsDiscussion()
  {
	  return discussionControl.getIsDiscussion();
  }
  
  /*
   * An accessor method that allows other classes to access the integer
   * value of the minutes left in the day or night
   * 
   * @return The number of minutes left in the day or night
   */
  public int getDayMinutes()
  {
	  return updateDayMinutes.getMinutes();
  }
  
  /*
   * An accessor method that allows other classes to access the integer
   * value of the seconds left in the day or night
   * 
   * @return The number of seconds left in the day or night
   */
  public int getDaySeconds()
  {
	  return updateDaySeconds.getSeconds();
  }
  
  /*
   * An accessor method that allows other classes to access the integer
   * value of the seconds left in the current discussion period
   * 
   * @return The number of seconds left in the day or night
   */
  public int getDiscussionSeconds()
  {
	  return updateDiscussionSeconds.getSeconds();
  }
  
  /*
   * Calling this method starts a countdown. At the end of the countdown, the day boolean value switches
   * to its opposite. 
   * 
   * @param the delay (in minutes) before the day/night value switches
   * @param the delay (in seconds) before the day/night value switches
   */
  public void startDayNightCountdown(int minutes, int seconds)
  {
	  gameTimer.schedule(dayNightCycle, (minutes * 60000) + (seconds * 100));
	  updateDayMinutes.setMinutes(minutes);
	  updateDaySeconds.setSeconds(seconds);
	  gameTimer.scheduleAtFixedRate(updateDaySeconds, 1000, 1000);
	  gameTimer.scheduleAtFixedRate(updateDayMinutes, 60000, 60000);
  }
  
  /*
   * Calling this method starts a countdown. At the end of the countdown, the discussable 
   * boolean value switches to its opposite.
   * 
   * @param the delay (in seconds) before the discussable value switches
   */
  public void startDiscussionCountdown(int seconds)
  {
	  gameTimer.schedule(discussionControl, seconds * 100); 
	  updateDiscussionSeconds.setSeconds(seconds);
	  gameTimer.scheduleAtFixedRate(updateDiscussionSeconds, 1000, 1000);
  }

}







