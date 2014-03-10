package net.piekarski.ti;

import com.google.inject.Inject;
import net.piekarski.ti.exception.CommandLineNotParsedException;
import net.piekarski.ti.exception.FileFormatNotSupportedException;
import net.piekarski.ti.io.reader.CSVTableReader;
import net.piekarski.ti.io.reader.ExcelTableReader;
import net.piekarski.ti.io.reader.LazyTableReader;
import net.piekarski.ti.io.writer.LazyTableWriter;
import net.piekarski.ti.io.writer.LiquibaseInsertWriter;
import net.piekarski.ti.io.writer.LiquibaseUpdateWriter;
import net.piekarski.ti.io.writer.ReplaceCharsForSqlTableWriter;
import net.piekarski.ti.io.writer.SqlInsertWriter;
import net.piekarski.ti.io.writer.SqlUpdateWriter;
import net.piekarski.ti.io.writer.StringTableWriter;
import net.piekarski.ti.io.writer.StringTableWriterAdapter;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

import static net.piekarski.ti.type.OptionType.HELP;
import static net.piekarski.ti.type.OptionType.INPUT;
import static net.piekarski.ti.type.OptionType.LIQUIBASE;
import static net.piekarski.ti.type.OptionType.OUTPUT;
import static net.piekarski.ti.type.OptionType.SEPARATOR;
import static net.piekarski.ti.type.OptionType.TABLE;
import static net.piekarski.ti.type.OptionType.UPDATE;

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

    public Converter getConverter() throws CommandLineNotParsedException, IOException,
            FileFormatNotSupportedException, XMLStreamException {
        if (cmd == null) {
            throw new CommandLineNotParsedException();
        }
        return createConverter();
    }

    private boolean hasOption(OptionType opt) {
        return cmd.hasOption(opt.getOpt());
    }

    private String getOptionValue(OptionType opt) {
        return cmd.getOptionValue(opt.getOpt());
    }

    private Converter createConverter() throws FileFormatNotSupportedException {
        return new Converter(getTableReader(), getTableWriter());
    }

    public LazyTableReader getTableReader() throws FileFormatNotSupportedException {
        File inputFile = new File(getOptionValue(INPUT));
        return getTableReader(inputFile);
    }

    public LazyTableWriter getTableWriter() {
        File outputFile = new File(getOptionValue(OUTPUT));
        return getTableWriter(outputFile);
    }

    private LazyTableReader getTableReader(File file) throws FileFormatNotSupportedException {
        if (file.getName().endsWith(".csv")) {
            return getCsvReader(file);
        } else if (file.getName().endsWith(".xls")) {
            return getExcelReader(file);
        }
        throw new FileFormatNotSupportedException();
    }

    private LazyTableReader getCsvReader(File file) {
        String separator = ";";
        if (hasOption(SEPARATOR)) {
            separator = getOptionValue(SEPARATOR);
        }
        return new CSVTableReader(file, separator);
    }

    private LazyTableReader getExcelReader(File file) {
        return new ExcelTableReader(file);
    }

    private LazyTableWriter getTableWriter(File outputFile) {
        return new StringTableWriterAdapter(getStringTableWriter(outputFile));
    }

    private StringTableWriter getStringTableWriter(File file) {
        return hasOption(LIQUIBASE) ?
                getLiquibaseTableWriter(file) :
                getSqlTableWriter(file);
    }

    private StringTableWriter getLiquibaseTableWriter(File file) {
        return hasOption(UPDATE) ?
                new LiquibaseUpdateWriter(file, getOptionValue(TABLE), getOptionValue(UPDATE)) :
                new LiquibaseInsertWriter(file, getOptionValue(TABLE));
    }

    private StringTableWriter getSqlTableWriter(File file) {
        return new ReplaceCharsForSqlTableWriter(getNoQuotedCellsSqlWriter(file));
    }

    private StringTableWriter getNoQuotedCellsSqlWriter(File file) {
        return hasOption(UPDATE) ?
                new SqlUpdateWriter(file, getOptionValue(TABLE), getOptionValue(UPDATE)) :
                new SqlInsertWriter(file, getOptionValue(TABLE));
    }
}