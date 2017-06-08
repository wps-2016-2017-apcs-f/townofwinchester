/*
 * ChatServer.java
 */
package townofwinchester;

import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import org.apache.logging.log4j.*;

/**
 * ChatServer is the main class for the server part of the chat
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
<<<<<<< HEAD
 * javadoc comments by:
 * @author Roy H. Xing
=======
 * @author Ethan Wong
 * @author Roy Xing
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
 */

public class ChatServer implements Runnable
{
   private static Logger logger = LogManager.getLogger("TOW");
   private ChatServerThread clients[] = new ChatServerThread[50];
   private ServerSocket server = null;
   private Thread       thread = null;
   private static Logger logger = LogManager.getLogger("ChatServer");
   private int clientCount = 0;

   public ChatServer(int port)
   {  try
<<<<<<< HEAD
      {  
		 logger.info("Binding to port " + port + ", please wait  ...");
         server = new ServerSocket(port);  
		 logger.info("Server started: " + server);
         start(); }
      catch(IOException ioe)
      {   
		 logger.info("Can not bind to port " + port + ": " + ioe.getMessage());
	  }
=======
      {  logger.info("Binding to port " + port + ", please wait  ...");
         server = new ServerSocket(port);  
         logger.info("Server started: " + server);
         start(); }
      catch(IOException ioe)
      {  logger.error("Can not bind to port " + port + ": " + ioe.getMessage()); }
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
   }
   
   /**
    * The run method that runs the actual client and adds
	* the initialized client to the thread
	* @return Nothing
	* @exception IOException on server accepting error
    */
   public void run()
   {  while (thread != null)
      {  try
<<<<<<< HEAD
         {  
			logger.info("Waiting for a client...");
            addThread(server.accept()); }
         catch(IOException ioe)
         {  
			logger.info("Server accept error: " + ioe);
			stop(); 
		 }
=======
         {  logger.info("Waiting for a client ..."); 
            addThread(server.accept()); }
         catch(IOException ioe)
         {  logger.error("Server accept error: " + ioe); stop(); }
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
      }
   }
   
   /**
    * The start method that starts the thread for the
	* clients
	* @return Nothing.
    */
   public void start()
   {  if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }
   }
   
   /**
    * The stop method that terminates the threads
	* of the clients
	* @return Nothing.
    */
   public void stop()
   {  if (thread != null)
      {  thread.stop(); 
         thread = null;
      }
   }
   
   /**
    * The method to find the client's ID
	*
	* @param ID This is the passed identification number of the client
	* @return int This returns the client's ID
    */
   private int findClient(int ID)
   {  for (int i = 0; i < clientCount; i++)
         if (clients[i].getID() == ID)
            return i;
      return -1;
   }
   
   /**
    * The method to handle the messages sent
	* @param input This is the string message to be handled
	* @param ID This is the passed identification number of the client
	* @return Nothing.
    */
   public synchronized void handle(int ID, String input)
   {  if (input.equals(".bye"))
      {  clients[findClient(ID)].send(".bye");
         remove(ID); }
      else
         for (int i = 0; i < clientCount; i++)
            clients[i].send(ID + ": " + input);   
   }
   
   /**
    * This is the method to remove a client from the chat
	* @param ID This is the passed identification number of the client
	* @return Nothing.
    */   
   public synchronized void remove(int ID)
   {  int pos = findClient(ID);
      if (pos >= 0)
      {  ChatServerThread toTerminate = clients[pos];
<<<<<<< HEAD
		 logger.info("Removing client thread " + ID + " at " + pos);
=======
         logger.info("Removing client thread " + ID + " at " + pos);
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
         if (pos < clientCount-1)
            for (int i = pos+1; i < clientCount; i++)
               clients[i-1] = clients[i];
         clientCount--;
         try
         {  toTerminate.close(); }
         catch(IOException ioe)
<<<<<<< HEAD
         {  logger.info("Error closing thread: " + ioe); }
=======
         {  logger.error("Error closing thread: " + ioe); }
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
         toTerminate.stop(); }
   }
   
   /**
    * This method adds a client thread to the socket for the chat
	* @param socket This is the socket to add to the thread
	* @return Nothing.
    */
   private void addThread(Socket socket)
   {  if (clientCount < clients.length)
<<<<<<< HEAD
      {  
		 logger.info("Client accepted: " + socket);
=======
      {  logger.info("Client accepted: " + socket);
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
         clients[clientCount] = new ChatServerThread(this, socket);
         try
         {  clients[clientCount].open(); 
            clients[clientCount].start();  
            clientCount++; }
         catch(IOException ioe)
<<<<<<< HEAD
         {  logger.info("Error opening thread: " + ioe); } }
      else
		 logger.info("Client refused: maximum " + clients.length + " reached.");
=======
         {  logger.error("Error opening thread: " + ioe); } }
      else
         logger.info("Client refused: maximum " + clients.length + " reached.");
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
   }
   
   /**
    * This is the main method
	* @param args This is the argument that allows for the port #
	* example of usage: java ClientServer 50501
	* @return Nothing
    */
   public static void main(String args[])
   {  ChatServer server = null;
      if (args.length != 1)
<<<<<<< HEAD
		 logger.info("Usage: java ChatServer port");
=======
         logger.info("Usage: java ChatServer port");
>>>>>>> 1813f0dfc21b78e4cb03e5a75b4d78da832c4fb6
      else
         server = new ChatServer(Integer.parseInt(args[0]));
   }
}