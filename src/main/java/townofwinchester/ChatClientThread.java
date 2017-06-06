/*
 * ChatClientThread.java
 */
package townofwinchester;

import java.net.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * ChatClientThread is the thread for the client part of the chat
 *
 * @see http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 * javadoc comments by:
 * @author Emily Lee
 * @author Roy H. Xing
 */

public class ChatClientThread extends Thread
{  private Socket           socket   = null;
   private ChatClient       client   = null;
   private DataInputStream  streamIn = null;
   private static Logger logger = LogManager.getLogger("ChatClientThread");

   public ChatClientThread(ChatClient _client, Socket _socket)
   {  client   = _client;
      socket   = _socket;
      open();  
      start();
   }
   public void open()
   {  try
      {  streamIn  = new DataInputStream(socket.getInputStream());
      }
      catch(IOException ioe)
      {  logger.error("Error getting input stream: " + ioe);
         client.stop();
      }
   }
   public void close()
   {  try
      {  if (streamIn != null) streamIn.close();
      }
      catch(IOException ioe)
      {  logger.error("Error closing input stream: " + ioe);
      }
   }
   public void run()
   {  while (true)
      {  try
         {  client.handle(streamIn.readUTF());
         }
         catch(IOException ioe)
         {  logger.error("Listening error: " + ioe.getMessage());
            client.stop();
         }
      }
   }
}
