/*
 * CLO.java
 */
package townofwinchester;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class CLO {
    /** Quoted command-line argument string. */
    private StringBuilder line;
    /** CLI command-line options. */
    private CommandLine clo;
    /** log4j logger. */
    private Logger logger = LogManager.getLogger(TownOfWinchester.SHORT);

    private static final String ARG_NAMES = "NAME";    
    private static final String ARG_MESSAGE
        = ARG_NAMES + "                       The name of this player";
    private static final String APP_MESSAGE
        = TownOfWinchester.LONG + " " + ARG_NAMES;

    public CLO(String[] args) {
        // Build line of quoted command-line arguments.
        String sep = "\"";
        line = new StringBuilder();
        for (String arg : args) {
            line.append(sep).append(arg); sep = " ";
        }
        if (line.length() > 0)
            line.append("\"");
        // Log command-line arguments.
        logger.debug("CLO: {}", line);
        // Create command-line options.
        Options options = new Options();
        options.addOption(Option.builder("c")
            .longOpt("client")
            .numberOfArgs(2)
            .argName("IP> <PORT")
            .desc("client mode (specify <IP> <PORT>); not with -s")
            .build());
        options.addOption(Option.builder("s")
            .longOpt("server")
            .numberOfArgs(1)
            .argName("PORT")
            .desc("server mode (specify <PORT>); not with -c")
            .build());
        options.addOption(Option.builder("?")
            .longOpt("help")
            .desc("print this message and exit")
            .build());
        try {
            // Parse command-line arguments as per the command-line options.
            CommandLineParser parser = new DefaultParser();
            clo = parser.parse(options, args);
        }
        catch (ParseException e) {
            // Print usage message, if command-line argument parsing error.
            String error = String.format("\n%s %s: %s\n\n%s",
                TownOfWinchester.LONG, line, e.getMessage(), ARG_MESSAGE);
            new HelpFormatter().printHelp(APP_MESSAGE, error, options, "", true);
            System.exit(64);
        }
        // Print usage message, if option "-?".
        if (clo.hasOption("?")) {
            new HelpFormatter().printHelp(APP_MESSAGE, options, true);
            System.exit(0);
        }
        // Log extra command-line arguments.
        logger.debug("CLO: {}", Arrays.asList(clo.getArgs()));
        // Print usage message, if missing one extra command-line argument.
        if (clo.getArgs().length != 1) {
            String error = String.format("\n%s %s: %s\n\n%s",
                TownOfWinchester.LONG, line, "one argument expected", ARG_MESSAGE);
            new HelpFormatter().printHelp(APP_MESSAGE, error, options, "", true);
            System.exit(64);
        }
    }

    public boolean isClient() {
        return clo.hasOption("c") && !clo.hasOption("s");
    }
    public boolean isServer() {
        return !clo.hasOption("c") && clo.hasOption("s");
    }

    public int getPort() {
        assert isClient() != isServer() : "getPort(): is not client or server";
        if (isClient()) {
            String[] args = clo.getOptionValues("c");
            logger.debug("getPort(): -c {}", Arrays.asList(args));
            return Integer.parseInt(args[1]);
        }
        else /* if (isServer()) */ {
            String[] args = clo.getOptionValues("s");
            logger.debug("getPort(): -s {}", Arrays.asList(args));
            return Integer.parseInt(args[0]);
        }
    }
    public String getHost() {
        assert isServer() : "getHost(): is not server";
        String[] args = clo.getOptionValues("c");
        logger.debug("getHost(): -c {}", Arrays.asList(args));
        return args[0];
    }
}