package net.piekarski.ti;

import com.google.inject.Inject;
import net.piekarski.ti.exception.CommandLineNotParsedException;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineService {
    protected Options options;
    private HelpFormatter helpFormatter;
    private CommandLineParser parser;
    protected CommandLine cmd;

    @Inject
    public CommandLineService(Options options, HelpFormatter helpFormatter, CommandLineParser parser) {
        this.options = options;
        this.helpFormatter = helpFormatter;
        this.parser = parser;
    }

    public void parse(String[] args) throws ParseException {
        cmd = parser.parse(options, args);
    }

    public void printHelp() {
        helpFormatter.printHelp("table-importer", options);
    }

    public boolean hasHelpOption() throws CommandLineNotParsedException {
        if (cmd == null) {
            throw new CommandLineNotParsedException();
        }
        return hasOption(OptionType.HELP);
    }

    public boolean hasOption(OptionType opt) throws CommandLineNotParsedException {
        if (cmd == null) {
            throw new CommandLineNotParsedException();
        }
        return cmd.hasOption(opt.getOpt());
    }

    public String getOptionValue(OptionType opt) throws CommandLineNotParsedException {
        if (cmd == null) {
            throw new CommandLineNotParsedException();
        }
        return cmd.getOptionValue(opt.getOpt());
    }
}