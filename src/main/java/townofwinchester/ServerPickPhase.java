package townofwinchester;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

/*
 * GUI. Does stuff.
 * 
 * @author David Mao
 * 
 * TODO:
 * Replace playerCount with a role variable and timer with an actual timer, and make all the inputs work
 * 
 */

public class ServerPickPhase extends JFrame implements ActionListener{

    // these are the components we need.
    private final JPanel panel;       // container panel for the top
    private final JLabel role;           //role of player
    private final JLabel counter;        //role of server
    private final JLabel playerCounter;  //current player count
    private final JButton button;         //"confirm" button
    private final int people = 8;         //add # of people here?
    private String playerCount = "Recommended Player Count";
    private int villagerCount = 6;
    private int mafiaCount = 2;
    private int villagerCountC = 8;
    private int mafiaCountC = 0;
    private boolean vote = false;
    
    public ServerPickPhase(){

        panel = new JPanel();         //top component
        
        button = new JButton("Confirm");
        button.addActionListener( new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            System.out.println("Lol doesn't work yet");
          }
        });

        //window
        setPreferredSize(new Dimension(Math.max(800, 120*people + 100), 400));     //initial window is 1000x600 pixels, though width expands as needed
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(panel);

        //panel
        GridBagLayout testLayout = new GridBagLayout();
        panel.setLayout(testLayout);
        GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(Color.WHITE);
        
        final int[] arr = new int[people];                  //arr of votes
        for(int i = 0; i < people; i++){                      //makes buttons and vote tally
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = i;
          c.gridy = 3;
          final int temp = i;
          arr[temp] = 0;
          int sum = 0;
          for(int n : arr){
            sum += n;
          }
          final JLabel b = new JLabel("        Villager");
          b.setOpaque(true);
          b.setBackground(Color.GREEN);
          b.setPreferredSize(new Dimension(100, 40));
          panel.add(b, c);
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = i;
          c.gridy = 1;
          JButton a = new JButton("Person " + (i+1));
          a.setPreferredSize(new Dimension(120, 40));
          a.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              arr[temp] = Math.abs(1 - arr[temp]);
              if(arr[temp] == 1){
                b.setText("          Mafia");
                b.setBackground(Color.RED);
                b.setForeground(Color.BLACK);
                int sum = 0;
                for(int n : arr){
                  sum += n;
                }
                mafiaCountC = sum;
                villagerCountC = people - mafiaCountC;
                playerCounter.setText("Current Player Count - Villagers: " + villagerCountC + " - Mafia: " + mafiaCountC);
              }
              else{
                b.setText("        Villager");
                b.setBackground(Color.GREEN);
                b.setForeground(Color.BLACK);
                int sum = 0;
                for(int n : arr){
                  sum += n;
                }
                mafiaCountC = sum;
                villagerCountC = people - mafiaCountC;
                playerCounter.setText("Current Player Count - Villagers: " + villagerCountC + " - Mafia: " + mafiaCountC);
              }
            }
          });
          panel.add(a, c);
        }
        
        for(int j = 0; j < people; j++){                      //makes pictures **TEMP**
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = j;
          c.gridy = 2;
          JLabel d = new JLabel("       Picture " + (j+1));
          d.setPreferredSize(new Dimension(100, 40));
          panel.add(d, c);
        }
        
        role = new JLabel(playerCount + " - Villagers: " + villagerCount + " - Mafia: " + mafiaCount, SwingConstants.LEADING);
        role.setFont(new Font("Inconsolata", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50; //increase height of the title
        c.weightx = 0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(role, c);
        
        counter = new JLabel("Role: God", SwingConstants.TRAILING);
        counter.setFont(new Font("Inconsolata", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50; //increase height of the title
        c.weightx = 0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(counter, c);

        playerCounter = new JLabel("Current Player Count - Villagers: " + villagerCountC + " - Mafia: " + mafiaCountC, SwingConstants.CENTER);
        playerCounter.setFont(new Font("Inconsolata", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50; //increase height of the title
        c.weightx = 0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(playerCounter, c);
        
        panel.add(button);
        
        pack();   //makes sure every layout and size we just defined gets applied before the stuff becomes visible
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
          @Override
            public void run(){
                new ServerPickPhase().setVisible(true);
            }
        });
    }
}