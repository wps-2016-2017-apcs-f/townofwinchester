/*
 * ChatClient.java
 */
package townofwinchester;

import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

/**
 * ChatClient is the main class for the client part of the chat THIS IS A TEST DELETE THIS PART
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 *
 * @author Emily Lee
 * @author Samuel Lee
 * @author Roy H. Xing
 * 
 */

public class ChatClient implements Runnable
{  private Socket socket              = null;
   private Thread thread              = null;
   private BufferedReader   console   = null;
   private DataOutputStream streamOut = null;
   private ChatClientThread client    = null;
   private static Logger logger = LogManager.getLogger("ChatClient");
   private volatile boolean runningThread = true;
   private String name = null;

   public ChatClient(String serverName, int serverPort, String name){
   logger.info("Establishing connection. Please wait ...");
   this.name = name;
   
      try
      {  socket = new Socket(serverName, serverPort);
         logger.info("Connected: " + socket);
         System.out.println("Welcome " + name + "! :^)");
         start();
         streamOut.writeUTF(name + ": has joined the game");
      }
      catch(UnknownHostException uhe){
    logger.info("Host unknown: " + uhe.getMessage());
   }
      catch(IOException ioe){
    logger.info("Unexpected exception: " + ioe.getMessage());
   }
   }
   public String getName()
   {
     return this.name;
   }
   public void run()
   {  while (thread != null && runningThread)
      {  try
         {  streamOut.writeUTF(console.readLine());
            streamOut.flush();
         }
         catch(IOException ioe)
         {
   logger.info("Sending error: " + ioe.getMessage());
            stop();
         }
      }
   }
   public void handle(String msg)
   {  if (msg.contains(".bye"))
      {  
   logger.info("Good bye. Press RETURN to exit...");
         stop();
      }
      else
         System.out.println(msg);
   }
   public void start() throws IOException
   {  console   = new BufferedReader(new InputStreamReader(System.in));
      streamOut = new DataOutputStream(socket.getOutputStream());
      if (thread == null)
      {  client = new ChatClientThread(this, socket);
         thread = new Thread(this);                   
         thread.start();
      }
   }
   public void stop(){
    if(thread != null){
     runningThread = false;
     //thread = null;
  }
       try{
   if (console   != null)  console.close();
         if (streamOut != null)  streamOut.close();
         if (socket    != null)  socket.close();
      }
      catch(IOException ioe){
    logger.info("Error, closing..."); }
    client.close();  
          try {
   Thread.sleep(5000);
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   logger.error("Thread interruption failed");
  }
          client.interrupt();
   }
   public static void main(String args[])
   {  ChatClient client = null;
      if (args.length != 2)
   logger.info("You did not execute the program correctly, do this: Usage: java ChatClient host port");
      else
         client = new ChatClient(args[0], Integer.parseInt(args[1]), args[2]);
   }
}