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
    /** log4j logger */
    private static Logger logger = LogManager.getLogger("TownOfWinchester");

    /**
     * The SpaceInvader main method that initializes the entire game.
     *
     * @param args command-line argument array
     */
    public static void main(String[] args) {
        logger.info("# TownOfWinchester");
        CLO clo = new CLO(args);
        logger.info("client = {}; server = {};", clo.isClient(), clo.isServer());
    }
}