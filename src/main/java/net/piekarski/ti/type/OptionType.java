package net.piekarski.ti.type;

import org.apache.commons.cli.Option;

public enum OptionType {
    HELP("h", "help", false, "print help", false),
    INPUT("i", "input", true, "input file", true),
    OUTPUT("o", "output", true, "output file", true),
    TABLE("t", "table", true, "input file", true),
    INSERT("I", "insert", false, "insert", false),
    UPDATE("U", "update", true, "update with the key", false),
    LIQUIBASE("l", "liquibase", false, "generate Liquibase changesets", false),
    SQL("s", "sql", false, "generate sql", false),
    SEPARATOR("S", "separator", true, "csv separator", false);

    private Option option;

    OptionType(String shortName, String longName, boolean hasArg, String description, boolean required) {
        option = new Option(shortName, longName, hasArg, description);
        option.setRequired(required);
    }

    public Option getOption() {
        return option;
    }

    public String getOpt() {
        return option.getOpt();
    }

    public String getLongOpt() {
        return option.getLongOpt();
    }

    public String getDescription() {
        return option.getDescription();
    }
}
