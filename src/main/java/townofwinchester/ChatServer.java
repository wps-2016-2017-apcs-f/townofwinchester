/*
 * ChatServer.java
 */
package townofwinchester;

import java.net.*;
import java.io.*;
import org.apache.logging.log4j.*;
import java.util.Scanner;

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
   private Socket socket = null;
   private BufferedReader console = null;
   private OutputStream outStream = null;
   private PrintWriter toClient = null;
   private Thread thread = null;
   private int clientCount = 0;
   private String name = null;
   private boolean gameStart = false;
   private int gameStartCount = 0;
   private ChatClient serverChat = null;
   private MessageQueue<String> msgQueue;

   public ChatServer(int port, String name) {
	   msgQueue = new MessageQueue<String>();
	   try {
		   logger.info("Binding to port " + port + ", please wait  ...");
		   this.name = name;
		   server = new ServerSocket(port);  
		   logger.info("Server started: " + server);
		   start(); 
    }
	   catch(IOException ioe) {  
		   logger.error("Can not bind to port " + port + ": " + ioe.getMessage()); 
	   }
	   try{
		   Socket socket = server.accept();
	   }
	   catch(IOException ioe){
		   logger.error("socket did not work");
	   }
   }
   
   /** 
    * Server opens the chat window.
    * 
    * @param args is not used
    * 
    * @PostCondition Logger info displays that server is waiting for a client.
    */
   public void run() {
	   String line;
	   try{
			server.setSoTimeout(500); //This makes the server.accept() terminate after 5 seconds of waiting
	   }
	   catch(SocketException ex){
		   logger.error("Was not able to set the timeout for the server socket");
	   }
	   while (thread != null) {
		   try {
			   if(gameStart == false){
					logger.info("Waiting for a client ...");
					try{
						addThread(server.accept());
					}
					catch(SocketTimeoutException ste){
						logger.error("server.accept() timed out after 500 millisecondsseconds");
					}
			   }
				else{
					while(gameStart) {
						talkToClients(console.readLine());
					}
				}
			}
		   catch(IOException ioe) {  
			   logger.error("Server accept error: " + ioe); stop(); 
		   }
	   }
   }
   
   /** 
    * Initiates the thread
    * 
    * @params args is not used
    * 
    * @PostCondition Thread is initiated
    */
   public void start() {
	   console   = new BufferedReader(new InputStreamReader(System.in));
	   if (thread == null)
	    	thread = new Thread(this); 
       thread.start();
   }
   
   /** 
    * Thread is stopped by interruption. 
    * 
    * @param args is not used
    * 
    */
   public void stop() {  
	   if (thread != null) {  
        	try {
        		Thread.sleep(5000);
        } 
        	catch(InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		logger.error("Thread interruption failed");
        	}
        	thread.interrupt();
        	thread = null;
       }
   }
   
   /** 
    * Checks for client by comparing ID numbers.
    * 
    * @param ID the given ID from test classes
    * 
    * @return location of client. Returns -1 if search for client unsuccessful.
    */
   private int findClient(int ID) {
	   for (int i = 0; i < clientCount; i++)
		   if (clients[i].getID() == ID)
			   return i;
	   return -1;
   }
   
   /** 
    * Responds to client inputs.
    * 
    * @param ID client ID
    * 
    * @param input the client input
    * 
    * @PostCondition sends a client's message or calls the remove method for the client
    */
   public synchronized void handle(int ID, String input) {
	   msgQueue.enqueue(input);
	   //System.out.println(msgQueue);
	   if (input.contains(".bye")) {
		   clients[findClient(ID)].send(".bye");
		   remove(ID); 
	   }
	   else if(input.contains(".gameStart")){
		   gameStartCount++;
		   System.out.println("gameStartCount: " + gameStartCount + " , ClientCount: " + clientCount);
		   if(gameStartCount == clientCount){
			   gameStart = true;
		   }
	   }
	   else {
		   for (int i = 0; i < clientCount; i++){
			   clients[i].send(msgQueue.peek());
		   }
	   }
	   logger.info(input);
   }
   
   public void talkToClients(String serverMsg){
		for (int i = 0; i < clientCount; i++){
			clients[i].send("GOD: " + serverMsg);
		}
   }
   
   public int getClientCount(){
	   return clientCount;
   }
   
   public ChatServerThread getClients(int i){
	   return clients[i];
   }
   
   /**
    * Removes the client.
    * 
    * @param ID client ID
    * 
    * @PostCondition removes client from chat.
    */
   public synchronized void remove(int ID) {
	   int pos = findClient(ID);
	   if (pos >= 0) {
		   ChatServerThread toTerminate = clients[pos];
		   logger.info("Removing client thread " + ID + " at " + pos);
		   if (pos < clientCount-1)
			   for (int i = pos+1; i < clientCount; i++)
				   clients[i-1] = clients[i];
		   clientCount--;
		   try {
			   toTerminate.close(); 
		   }
		   catch(IOException ioe) {
			   logger.error("Error closing thread: " + ioe); 
		   }
		   try {
			   Thread.sleep(5000);
		   }
		   catch (InterruptedException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			   logger.error("Thread interruption failed");
		   }
		   toTerminate.interrupt();
	   }
   }
   
   /** 
    * Adds socket to the thread.
    * 
    * @param socket client socket
    * 
    * @PostCondition client thread is logged.
    */
   private void addThread(Socket socket) {
	   if (clientCount < clients.length) {
		   logger.info("Client accepted: " + socket);
		   clients[clientCount] = new ChatServerThread(this, socket);
		   try {  
			   clients[clientCount].open(); 
			   clients[clientCount].start();  
			   clientCount++;
			   if(clientCount == 7) gameStart = true;
		   }
		   catch(IOException ioe) {
			   logger.error("Error opening thread: " + ioe);
		   } 
	   }
	   else
		   logger.info("Client refused: maximum " + clients.length + " reached.");
   }
   
   /** 
    * Accessor method for name.
    */
   public String getName() {
	   return this.name;
   }
   
   /**
    * Returns client name.
    * 
    * @param ID client ID
    * 
    * @return client name
    */
   public String getClientName(int ID) {
	   return clients[findClient(ID)].getName();
   }
   
   /**
    * The main method for this class sets the server to a new ChatServer
    * @param args is not used
    */
   public static void main(String args[]) {
	   ChatServer server = null;
	   if (args.length != 1)
		   logger.info("Usage: java ChatServer port");
	   else
		   server = new ChatServer(Integer.parseInt(args[0]), args[1]);
   }
}