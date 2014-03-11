package net.piekarski.ti;

import com.google.inject.Inject;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static net.piekarski.ti.type.OptionType.HELP;

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

    public boolean hasHelpOption() {
        return hasOption(HELP);
    }

    public boolean hasOption(OptionType opt) {
        return cmd.hasOption(opt.getOpt());
    }

    public String getOptionValue(OptionType opt) {
        return cmd.getOptionValue(opt.getOpt());
    }
}