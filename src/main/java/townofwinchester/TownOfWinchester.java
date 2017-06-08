/*
 * TownOfWinchester.java
 */
package townofwinchester;
import org.apache.logging.log4j.*;
import java.util.*;
import java.io.*;

/**
 * TownOfWinchester is the main class for the TownOfWinchester game.
 *
 * @author David C. Petty
 */
public class TownOfWinchester {
    /** LONG name of this project. */
    public static final String LONG = "TownOfWinchester";
    /** SHORT name of this project. */
    public static final String SHORT = "TOW";
    /** log4j logger. */
    private static Logger logger = LogManager.getLogger(SHORT);

    /**
     * The SpaceInvader main method that initializes the entire game.
     *
     * @param args command-line argument array
     */
    public static void main(String[] args) {
        logger.info("# TownOfWinchester");
        CLO clo = new CLO(args);
		if(clo.isClient()){
			new ChatClient(clo.getHost(), clo.getPort());
			System.exit(0);
		}
		if(clo.isServer()){
			new ChatServer(clo.getPort());
			System.exit(0);
		}
        logger.info("client = {}; server = {};", clo.isClient(), clo.isServer());
		
    }
}