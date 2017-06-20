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
 * Replace villager with a role variable and timer with an actual timer, and make all the inputs work
 * 
 */

public class ClientWindow extends JFrame implements ActionListener{

    // these are the components we need.
    private final JSplitPane splitPane;  // split the window in top and bottom
    private final JPanel topPanel;       // container panel for the top
    private final JPanel bottomPanel;    // container panel for the bottom
    private final JScrollPane scrollPane; // makes the text scrollable
    private final JTextArea textArea;     // the text
    private final JPanel inputPanel;      // under the text a container for all the input elements
    private final JTextField textField;   // a textField for the text the user inputs
    private final JButton button;         // and a "send" button
    private final JLabel role;           //role of player
    private final JLabel counter;        //amount of time left
    private final int people = 8;         //add # of people here?
    private boolean day = true;           //day/night
    private String villager = "Villager";
    private boolean vote = false;
    private int sum;
    
    public ClientWindow(){

        splitPane = new JSplitPane();

        topPanel = new JPanel();         //top component
        bottomPanel = new JPanel();      //bottom component

        //text area
        scrollPane = new JScrollPane();
        textArea = new JTextArea();

        //input components
        inputPanel = new JPanel();
        textField = new JTextField();
        button = new JButton("send");
        button.addActionListener( new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            System.out.println("Lol doesn't work yet");
          }
        });

        //window
        setPreferredSize(new Dimension(Math.max(800, 120*people + 100), 600));     //initial window is 1000x600 pixels, though width expands as needed
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(300);                    //initial position of the divider is 200 (window is 600 pixels high)
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);

        //topPanel
        GridBagLayout testLayout = new GridBagLayout();
        topPanel.setLayout(testLayout);
        GridBagConstraints c = new GridBagConstraints();
        if(day == true)
          topPanel.setBackground(Color.WHITE);
        else
          topPanel.setBackground(Color.BLACK);
        
        final boolean[] arr = new boolean[people];                  //arr of votes
        for(int i = 0; i < people; i++){                      //makes buttons and vote tally
          final int temp = i;
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = i;
          c.gridy = 3;
          int tallyA = (arr[temp]) ? 1 : 0;
          final JLabel b = new JLabel("  Lynch vote: " + tallyA);
          b.setPreferredSize(new Dimension(100, 40));
          topPanel.add(b, c);
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = i;
          c.gridy = 1;
          JButton a = new JButton("Person " + (i+1));
          a.setPreferredSize(new Dimension(120, 40));
          a.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              int z = 0;
              for(int n = 0; n < arr.length; n++){
                int m = (arr[n]) ? 1 : 0;
                z += m;
              }
              sum = z;
              System.out.println(sum);
              if(sum != 0)
                System.out.println("Cannot Vote Twice");
              else{
              arr[temp] = !arr[temp];
              int tallyB = (arr[temp]) ? 1 : 0;
              b.setText("  Lynch vote: " + tallyB);
              }
            }
          });
          topPanel.add(a, c);
        }
        
        for(int j = 0; j < people; j++){                      //makes pictures **TEMP**
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = j;
          c.gridy = 2;
          JLabel d = new JLabel("       Picture " + (j+1));
          d.setPreferredSize(new Dimension(100, 40));
          topPanel.add(d, c);
        }
        
        role = new JLabel(villager, SwingConstants.LEADING);
        role.setFont(new Font("Inconsolata", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50; //increase height of the title
        c.weightx = 0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        topPanel.add(role, c);
        
        counter = new JLabel("Counter - 10:00", SwingConstants.TRAILING);
        counter.setFont(new Font("Inconsolata", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50; //increase height of the title
        c.weightx = 0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        topPanel.add(counter, c);
        
        //bottomPanel
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); //bottomPanel is arranged vertically

        bottomPanel.add(scrollPane);
        scrollPane.setViewportView(textArea);
        bottomPanel.add(inputPanel);
        
        //max size of the inputPanel (so it doesn't get too big when the user resizes the window)
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));     //max height: 100    max width: a big ass number
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        inputPanel.add(textField);        //left is the textField
        inputPanel.add(button);           //right is the "send" button

        pack();   //makes sure every layout and size we just defined gets applied before the stuff becomes visible
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
          @Override
            public void run(){
                new ClientWindow().setVisible(true);
            }
        });
    }
}