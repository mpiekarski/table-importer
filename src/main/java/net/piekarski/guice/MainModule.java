package net.piekarski.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.CommandLineService;
import net.piekarski.Main;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.PosixParser;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Main.class).in(Singleton.class);
        bind(CommandLineService.class).in(Singleton.class);
        bind(HelpFormatter.class).in(Singleton.class);
        bind(CommandLineParser.class).to(PosixParser.class).in(Singleton.class);
    }
}
