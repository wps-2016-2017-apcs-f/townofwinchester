/*
 * TownOfWinchester.java
 */
package townofwinchester;
import java.util.*;

/**
 * TownOfWinchester is the main class for the TownOfWinchester game.
 *
 * @author David C. Petty
 */
public class TownOfWinchester {

    /** true if client. */
    private static boolean isClient = false;
    /** true if server. */
    private static boolean isServer = false;

    private static final List<String> options = new ArrayList<String>() {
        { add("-c"); add("--client"); add("-s"); add("--server"); }
    };

    /**
     * The SpaceInvader main method that initializes the entire game.
     *
     * @param args command-line argument array
     */
    public static void main(String[] args) {
        System.out.println("# TownOfWinchester");
        // Parse command-line options.
        for (String opt : args) {
            if (!options.contains(opt))
                throw new RuntimeException("bad option: " + opt);
            if (opt.equals("-c") || opt.equals("--client")) isClient = true;
            if (opt.equals("-s") || opt.equals("--server")) isServer = true;
        }
        System.out.printf("client = %s; server = %s;\n", isClient, isServer);
    }
}
