package net.piekarski.ti;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.piekarski.ti.exception.TableImporterException;
import net.piekarski.ti.guice.MainModule;
import org.apache.commons.cli.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    private CommandLineService cmd;

    @Inject
    public Main(CommandLineService cmd) {
        this.cmd = cmd;
    }

    protected void start(String[] args) {
        try {
            tryToStart(args);
        } catch (Exception e) {
            System.out.println(e.getMessage() == null ? e.toString() : e.getMessage());
            cmd.printHelp();
        }
    }

    private void tryToStart(String[] args) throws ParseException, IOException, XMLStreamException,
            TableImporterException {
        cmd.parse(args);

        if (cmd.hasHelpOption()) {
            cmd.printHelp();
            return;
        }

        cmd.getConverter().run();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MainModule());
        Main main = injector.getInstance(Main.class);
        main.start(args);
    }
}
