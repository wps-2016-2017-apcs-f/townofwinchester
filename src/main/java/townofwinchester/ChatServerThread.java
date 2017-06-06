/*
 * ChatServerThread.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;

import java.net.*;
import java.io.*;

/**
 * ChatServerThread is the thread for the server part of the chat
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 * javadoc comments by:
 * @author Samuel Lee
 * @author Lulu Tian
 * @author Roy H. Xing
 */

public class ChatServerThread extends Thread
{  private ChatServer       server    = null;
   private Socket           socket    = null;
   private int              ID        = -1;
   private DataInputStream  streamIn  =  null;
   private DataOutputStream streamOut = null;
   
   private static Logger logger = LogManager.getLogger("TownOfWinchester");

   public ChatServerThread(ChatServer _server, Socket _socket)
   {  super();
      server = _server;
      socket = _socket;
      ID     = socket.getPort();
   }
   
   /**
    * This is the method that sends the string message
 * @param msg This is the string message to be sent
 * @return Nothing.
    */
   public void send(String msg)
   {   try
       {  streamOut.writeUTF(msg);
          streamOut.flush();
       }
       catch(IOException ioe)
       {  logger.error(ID + " ERROR sending: " + ioe.getMessage());
          server.remove(ID);
          stop();
       }
   }
   
   /**
    * This is the method that gets the ID number of the client
 *
 *@return int This is the ID of the client
    */
   public int getID()
   {
    return ID;
   }
   
   /**
    * This is the method that runs the thread
 * 
 * @return Nothing.
    */
   public void run()
   {  logger.info("Server Thread " + ID + " running.");
      while (true)
      {  try
         {  server.handle(ID, streamIn.readUTF());
         }
         catch(IOException ioe)
         {  logger.error(ID + " ERROR reading: " + ioe.getMessage());
            server.remove(ID);
            stop();
         }
      }
   }
   
   /**
    * This is the method that opens the I/O streams
 * @exception IOException
 * @return Nothing.
    */
   public void open() throws IOException
   {  streamIn = new DataInputStream(new 
                        BufferedInputStream(socket.getInputStream()));
      streamOut = new DataOutputStream(new
                        BufferedOutputStream(socket.getOutputStream()));
   }
   
   /**
    * This is the method that closes the socket and the I/O streams
 * @exception IOException
 * @return Nothing.
    */
   public void close() throws IOException
   {  if (socket != null)    socket.close();
      if (streamIn != null)  streamIn.close();
      if (streamOut != null) streamOut.close();
   }
}