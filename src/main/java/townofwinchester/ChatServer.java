/*
 * ChatServer.java
 */
package townofwinchester;

import java.net.*;
import java.io.*;
import org.apache.logging.log4j.*;

/**
 * ChatServer is the main class for the server part of the chat
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 * @author Emily Lee
 * @author Samuel Lee
 * @author Lulu Tian
 * @author Ethan Wong
 * @author Roy Xing
 */

public class ChatServer implements Runnable
{
   private static Logger logger = LogManager.getLogger("TOW");
   private ChatServerThread clients[] = new ChatServerThread[50];
   private ServerSocket server = null;
   private Thread       thread = null;
   private int clientCount = 0;
   private String name = null;

   public ChatServer(int port, String name)
   {  try
      {  logger.info("Binding to port " + port + ", please wait  ...");
         this.name = name;
         server = new ServerSocket(port);  
         logger.info("Server started: " + server);
         start(); }
      catch(IOException ioe)
      {  logger.error("Can not bind to port " + port + ": " + ioe.getMessage()); }
   }
   /* Server opens the chat window.
    * 
    * @PostCondition Logger info displays that server is waiting for a client.
    */
   public void run()
   {  while (thread != null)
      {  try
         {  logger.info("Waiting for a client ..."); 
            addThread(server.accept()); }
         catch(IOException ioe)
         {  logger.error("Server accept error: " + ioe); stop(); }
      }
   }
   /* Initiates the thread.
    * 
    * @PostCondition Thread is initiated.
    */
   public void start()
   {  if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }
   }
   /* Thread is stopped by interruption. 
    * 
    */
   public void stop()
   {  if (thread != null)
      {  try {
  Thread.sleep(5000);
 } catch (InterruptedException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
  logger.error("Thread interruption failed");
 }
        thread.interrupt();
         thread = null;
      }
   }
   /* Checks for client by comparing ID numbers.
    * 
    * @param ID the given ID from test classes
    * @return location of client. Returns -1 if search for client unsuccessful.
    */
   private int findClient(int ID)
   {  for (int i = 0; i < clientCount; i++)
         if (clients[i].getID() == ID)
            return i;
      return -1;
   }
   /* Responds to client inputs.
    * 
    * @param ID client ID
    * @param input the client input
    * 
    * @PostCondition sends a client's message or calls the remove method for the client
    */
   public synchronized void handle(int ID, String input)
   {  if (input.equals(".bye"))
      {  clients[findClient(ID)].send(".bye");
         remove(ID); }
      else
         for (int i = 0; i < clientCount; i++)
            clients[i].send(ID + ": " + input);   
   }
   /* Removes the client.
    * 
    * @param ID client ID
    * 
    * @PostCondition removes client from chat.
    */
   public synchronized void remove(int ID)
   {  int pos = findClient(ID);
      if (pos >= 0)
      {  ChatServerThread toTerminate = clients[pos];
         logger.info("Removing client thread " + ID + " at " + pos);
         if (pos < clientCount-1)
            for (int i = pos+1; i < clientCount; i++)
               clients[i-1] = clients[i];
         clientCount--;
         try
         {  toTerminate.close(); 
         }
         catch(IOException ioe)
         {  logger.error("Error closing thread: " + ioe); }
         try 
         {
        	Thread.sleep(5000);
         } 
         catch (InterruptedException e) 
         {
        	 // TODO Auto-generated catch block
        	 e.printStackTrace();
        	 logger.error("Thread interruption failed");
         }
         toTerminate.interrupt();
         }
   }
   /* Adds socket to the thread.
    * 
    * @param socket client socket
    * 
    * @PostCondition client thread is logged.
    */
   private void addThread(Socket socket)
   {  if (clientCount < clients.length)
      {  logger.info("Client accepted: " + socket);
         clients[clientCount] = new ChatServerThread(this, socket);
         try
         {  clients[clientCount].open(); 
            clients[clientCount].start();  
            clientCount++; }
         catch(IOException ioe)
         {  logger.error("Error opening thread: " + ioe); } 
      }
      else
         logger.info("Client refused: maximum " + clients.length + " reached.");
   }
   /* 
    * Accessor method for name.
    */
   public String getName()
   {
     return this.name;
   }
   /*
    * Returns client name.
    * 
    * @param ID client ID
    * 
    * @return client name
    */
   public String getClientName(int ID)
   {
     return clients[findClient(ID)].getName();
   }
   public static void main(String args[])
   {  ChatServer server = null;
      if (args.length != 1)
         logger.info("Usage: java ChatServer port");
      else
        server = new ChatServer(Integer.parseInt(args[0]), args[1]);
   }
}