package townofwinchester;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class GameElements extends JFrame{

    // these are the components we need.
    private final int people = 8;         //add # of people here?
    private boolean day = true;           //day/night
    private String villager = "Villager";
    private boolean vote = false;
    public int sum;
    
    public GameElements(){
    }
    
    public boolean getDay(){
      return day;
    }
    
    public String getVillager(){
      return villager;
    }
    
    public boolean getVote(){
      return vote;
    }
    
    public int getSum(){
      return sum;
    }
    
    public int getPeople(){
      return people;
    }
}