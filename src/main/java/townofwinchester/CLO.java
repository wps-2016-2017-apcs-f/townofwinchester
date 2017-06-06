/*
 * CLO.java
 */
package townofwinchester;
import org.apache.commons.cli.*;

public class CLO {
    private StringBuilder line;
    private CommandLine clo;
    public CLO(String[] args) {
        line = new StringBuilder();
        for (String arg : args) { line.append(arg).append(" "); }
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
        try {
            CommandLineParser parser = new DefaultParser();
            clo = parser.parse(options, args);
        }
        catch (ParseException e) {
            String name = "TownOfWinchester";
            String message = String.format("\n%s %s: %s\n\n", name, line, e.getMessage());
            new HelpFormatter().printHelp(name, message, options, "", true);
            System.exit(64);
        }
    }
    public boolean isClient() {
        return clo.hasOption("c") && !clo.hasOption("s");
    }
    public boolean isServer() {
        return !clo.hasOption("c") && clo.hasOption("s");
    }
}
