package net.piekarski;

import com.google.inject.Inject;
import net.piekarski.exception.CommandLineNotParsedException;
import net.piekarski.exception.FileFormatNotSupportedException;
import net.piekarski.io.LiquibaseInsertWriter;
import net.piekarski.io.LiquibaseUpdateWriter;
import net.piekarski.io.QuotedCellsTableWriter;
import net.piekarski.io.SqlInsertWriter;
import net.piekarski.io.SqlUpdateWriter;
import net.piekarski.io.StringTableWriter;
import net.piekarski.io.StringTableWriterAdapter;
import net.piekarski.io.TableWriter;
import net.piekarski.io.reader.CSVTableReader;
import net.piekarski.io.reader.ExcelTableReader;
import net.piekarski.io.reader.LazyTableReader;
import net.piekarski.type.OptionType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static net.piekarski.type.OptionType.HELP;
import static net.piekarski.type.OptionType.INPUT;
import static net.piekarski.type.OptionType.LIQUIBASE;
import static net.piekarski.type.OptionType.OUTPUT;
import static net.piekarski.type.OptionType.SEPARATOR;
import static net.piekarski.type.OptionType.TABLE;
import static net.piekarski.type.OptionType.UPDATE;
import static net.piekarski.type.OptionType.values;

public class CommandLineService {
    protected Options options;
    private HelpFormatter helpFormatter;
    private CommandLineParser parser;
    protected CommandLine cmd;

    @Inject
    public CommandLineService(HelpFormatter helpFormatter, CommandLineParser parser) {
        this.options = createOptions();
        this.helpFormatter = helpFormatter;
        this.parser = parser;
    }

    public void parse(String[] args) throws ParseException {
        cmd = parser.parse(options, args);
    }

    public void printHelp() {
        helpFormatter.printHelp(Main.APPLICATION_NAME, options);
    }

    public boolean hasHelpOption() {
        return hasOption(HELP);
    }

    public Converter getConverter() throws CommandLineNotParsedException, IOException,
            FileFormatNotSupportedException, XMLStreamException {
        if (cmd == null) {
            throw new CommandLineNotParsedException();
        }
        return createConverter();
    }

    private Options createOptions() {
        Options options = new Options();
        for (OptionType type : values()) {
            options.addOption(type.getOption());
        }
        return options;
    }

    private boolean hasOption(OptionType opt) {
        return cmd.hasOption(opt.getOpt());
    }

    private String getOptionValue(OptionType opt) {
        return cmd.getOptionValue(opt.getOpt());
    }

    private Converter createConverter() throws IOException, FileFormatNotSupportedException, XMLStreamException {
        File inputFile = new File(getOptionValue(INPUT));
        File outputFile = new File(getOptionValue(OUTPUT));
        LazyTableReader tableReader = getTableReader(inputFile);
        TableWriter tableWriter = getTableWriter(outputFile);

        return new Converter(tableReader, tableWriter);
    }

    private LazyTableReader getTableReader(File file) throws FileNotFoundException, FileFormatNotSupportedException {
        if (file.getName().endsWith(".csv")) {
            return getCsvReader(file);
        } else if (file.getName().endsWith(".xls")) {
            return getExcelReader(file);
        }
        throw new FileFormatNotSupportedException();
    }

    private LazyTableReader getCsvReader(File file) throws FileNotFoundException {
        CSVTableReader reader = new CSVTableReader(file);
        if (hasOption(SEPARATOR)) {
            String separator = getOptionValue(SEPARATOR);
            reader.setColumnSeparator(separator.charAt(0));
        }
        return reader;
    }

    private LazyTableReader getExcelReader(File file) {
        return new ExcelTableReader(file);
    }

    private TableWriter getTableWriter(File outputFile) throws IOException, XMLStreamException {
        return new StringTableWriterAdapter(getStringTableWriter(outputFile));
    }

    private StringTableWriter getStringTableWriter(File file) throws IOException, XMLStreamException {
        return hasOption(LIQUIBASE) ?
                getLiquibaseTableWriter(file) :
                getSqlTableWriter(file);
    }

    private StringTableWriter getLiquibaseTableWriter(File file) throws IOException, XMLStreamException {
        return hasOption(UPDATE) ?
                new LiquibaseUpdateWriter(file, getOptionValue(TABLE), getOptionValue(UPDATE)) :
                new LiquibaseInsertWriter(file, getOptionValue(TABLE));
    }

    private StringTableWriter getSqlTableWriter(File file) throws IOException {
        return new QuotedCellsTableWriter(getNoQuotedCellsSqlWriter(file));
    }

    private StringTableWriter getNoQuotedCellsSqlWriter(File file) throws IOException {
        return hasOption(UPDATE) ?
                new SqlUpdateWriter(file, getOptionValue(TABLE), getOptionValue(UPDATE)) :
                new SqlInsertWriter(file, getOptionValue(TABLE));
    }
}