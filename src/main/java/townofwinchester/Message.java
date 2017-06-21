/*
 * Message.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;
import java.net.*;
import java.io.*;

/**
 * Messsage is the wrapper class for the messages that will be sent throughout the game
 *
 *
 * @author Jeremy Hughes
 * @author Ethan Wong
 *
 */
public class Message{  
    public static enum Type {CHAT, MCHAT, JOIN, PICK, TIME, MVOTE, VOTE};
    private Type type;
    private int ID;
    private String text;

    /** 
     * Describes the Message that is being Snet
     * 
     * @from args is used to indicate the character that is sending the message. 
     * 
     * @to args is used to indicate the character that will eventually receive the message and is able to access it. 
     *
     * @details describes the content of the message
     */
    //public Message(Character from, Character to, String details) {
    // sender = from;
    // receiver = to; 
    // message = details; 
    //}
    
    // Example: "CHAT:NAME:Hi, there!"
    public Message(String message) {
        String[] parts = message.split(":");
        assert parts.length >= 3 : message + " must have (at least) two ':'";
        for (Type type : Type.values())
            if (type.toString().equals(parts[0].toUpperCase())) {
                this.type = type;
                this.ID = parts[1];
                this.text = parts[2];
                for (int i = 3; i < parts.length; i++)
                    this.text += ":" + parts[i];
            }
        assert this.type != null && this.ID != null && this.text != null
        : "String message parse failed";
        LogManager.getLogger(TownOfWinchester.SHORT).debug("Message({})", toString());
    }

    public Message(Type type, int ID, String text) {
        this.type = type;
        this.ID = ID;
        this.text = text;
    }

    public Type getType() { return type; }
    public int getID() { return name; }
    public String getText() { return text; }

    public String toString() {
        return type.toString() + ":" + ID + ":" + text;
    }

    // RED_FLAG: obsolete methods

    private String nextCommand;

    public String assignRole(boolean indicateMafiaStatus, String name) {
        //Character character = new Character();
        //String name1 = character.getName();
        if (indicateMafiaStatus == true){ 
            nextCommand = name + "." + "toMafia()";
            return Type.PICK + "You are a Mafia";
        } 
        else{   
            nextCommand = name + "." + "toVillager()"; 
            return Type.PICK + name + "is a villager";
        } 
    }

    public String compileText(String name, String message) {
        return Type.CHAT + ":" + name + ":" + message; 
    }
}
