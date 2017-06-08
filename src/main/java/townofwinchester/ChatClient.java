/*
 * ChatClient.java
 */
package townofwinchester;

import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

//console.readline() and stop() thread is depracted
/**
 * ChatClient is the main class for the client part of the chat
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 *
 * @author Roy H. Xing
 */

public class ChatClient implements Runnable
{  private Socket socket              = null;
   private Thread thread              = null;
   private BufferedReader   console   = null;
   private DataOutputStream streamOut = null;
   private ChatClientThread client    = null;
   private static Logger logger = LogManager.getLogger("ChatClient");
   private volatile boolean runningThread = true;

   public ChatClient(String serverName, int serverPort){
	  logger.info("Establishing connection. Please wait ...");
      try
      {  socket = new Socket(serverName, serverPort);
         logger.info("Connected: " + socket);
         start();
      }
      catch(UnknownHostException uhe){
		  logger.info("Host unknown: " + uhe.getMessage());
	  }
      catch(IOException ioe){
		  logger.info("Unexpected exception: " + ioe.getMessage());
	  }
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
   {  if (msg.equals(".bye"))
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
          client.stop();
   }
   public static void main(String args[])
   {  ChatClient client = null;
      if (args.length != 2)
		 logger.info("You did not execute the program correctly, do this: Usage: java ChatClient host port");
      else
         client = new ChatClient(args[0], Integer.parseInt(args[1]));
   }
}