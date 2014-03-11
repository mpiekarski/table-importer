package net.piekarski.ti;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import net.piekarski.ti.exception.FileFormatNotSupportedException;
import net.piekarski.ti.exception.TableImporterException;
import net.piekarski.ti.guice.CSVReaderModule;
import net.piekarski.ti.guice.CommandLineModule;
import net.piekarski.ti.guice.ConstantsModule;
import net.piekarski.ti.guice.ConverterModule;
import net.piekarski.ti.guice.ExcelReaderModule;
import net.piekarski.ti.guice.FileModule;
import net.piekarski.ti.guice.LazyTableWriterModule;
import net.piekarski.ti.guice.LiquibaseInsertWriterModule;
import net.piekarski.ti.guice.LiquibaseUpdateWriterModule;
import net.piekarski.ti.guice.SqlInsertWriterModule;
import net.piekarski.ti.guice.SqlUpdateWriterModule;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class Main {
    private CommandLineService cmd;

    @Inject
    public Main(CommandLineService cmd) {
        this.cmd = cmd;
    }

    protected void run(String[] args) {
        try {
            tryToRun(args);
        } catch (Exception e) {
            System.out.println(e.getMessage() == null ? e.toString() : e.getMessage());
            cmd.printHelp();
        }
    }

    private void tryToRun(String[] args) throws ParseException, TableImporterException, XMLStreamException,IOException {
        cmd.parse(args);

        if (cmd.hasHelpOption()) {
            cmd.printHelp();
            return;
        }

        Injector injector = Guice.createInjector(getModules());
        injector.getInstance(Converter.class).run();
    }

    private List<Module> getModules() throws FileFormatNotSupportedException {
        return ImmutableList.<Module>builder()
                .add(new ConverterModule())
                .add(new ConstantsModule(cmd))
                .add(new FileModule(cmd))
                .add(new LazyTableWriterModule())
                .add(getReaderModule())
                .add(getWriterModule())
                .build();
    }

    private Module getReaderModule() throws FileFormatNotSupportedException {
        String fileName = cmd.getOptionValue(OptionType.INPUT);

        if (fileName.endsWith(".csv")) {
            return new CSVReaderModule();
        } else if (fileName.endsWith(".xls")) {
            return new ExcelReaderModule();
        }

        throw new FileFormatNotSupportedException();
    }

    private Module getWriterModule() {
        return cmd.hasOption(OptionType.LIQUIBASE) ?
                cmd.hasOption(OptionType.UPDATE) ? new LiquibaseUpdateWriterModule() : new LiquibaseInsertWriterModule() :
                cmd.hasOption(OptionType.UPDATE) ? new SqlUpdateWriterModule() : new SqlInsertWriterModule();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CommandLineModule());
        injector.getInstance(Main.class).run(args);
    }
}
