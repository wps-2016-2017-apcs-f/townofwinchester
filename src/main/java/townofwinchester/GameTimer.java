
package townofwinchester;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/*GameTimer.java
 * 
 * the in game timer that dictates day night cycle
 * 
 * @author Adrian Stone
 * @author Tim Dalton
 */

public class GameTimer implements ActionListener
{
  private int delay = 300000; // 5 mins for first day
  private Timer gameTime;
  private boolean isNight = false; 
  
  
  
  public GameTimer()
  {
    
    gameTime = new Timer(delay, this);
  
  }
  
  
  public Timer getGameTimer()
  {
    return gameTime;
  }
  
  public int getDelay()
  {
    return delay;
  }
  
  public boolean getIsNight()
  {
    return isNight;
  }
  
  /*action performed when the timer hits zero. Changes the day 
   * night cycle and resets the timer to either 5 or 10 minuets. 
   * 
   * @param ActionEvent
   *
   * @return nothing
   */
  
  public void actionPerformed (ActionEvent e)
  {
    if(isNight == false)
    {
      isNight = true;
      delay = 300000; // 5 mins
      gameTime.start();
    }
    if (isNight == true)
    {
      delay = 600000; // 10 mins
      isNight = false;
      gameTime.start();
    }
  }
    
    
   
    
}
  
      
    
  
  
  

