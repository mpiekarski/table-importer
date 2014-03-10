package net.piekarski.ti;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import net.piekarski.ti.exception.TableImporterException;
import net.piekarski.ti.guice.MainModule;
import net.piekarski.ti.io.reader.LazyTableReader;
import net.piekarski.ti.io.writer.LazyTableWriter;
import org.apache.commons.cli.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;

public class Main {
    private CommandLineService cmd;

    @Inject
    public Main(CommandLineService cmd) {
        this.cmd = cmd;
    }

    protected Set<Module> start(String[] args) {
        try {
            return tryToStart(args);
        } catch (Exception e) {
            System.out.println(e.getMessage() == null ? e.toString() : e.getMessage());
            cmd.printHelp();
            return null;
        }
    }

    private Set<Module> tryToStart(String[] args) throws ParseException, IOException, XMLStreamException,
            TableImporterException {
        cmd.parse(args);

        if (cmd.hasHelpOption()) {
            cmd.printHelp();
            return null;
        }

        LazyTableReader reader = cmd.getTableReader();
        LazyTableWriter writer = cmd.getTableWriter();

        new Converter(reader, writer).run();

        return null;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MainModule());
        Main main = injector.getInstance(Main.class);
        Set<Module> modules = main.start(args);
        injector.createChildInjector(modules);
        injector.getInstance(Converter.class);
    }
}
