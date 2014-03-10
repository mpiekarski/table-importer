package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.CommandLineService;
import net.piekarski.ti.Main;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import static net.piekarski.ti.type.OptionType.values;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Main.class).in(Singleton.class);
        bind(CommandLineService.class).in(Singleton.class);
        bind(HelpFormatter.class).in(Singleton.class);
        bind(CommandLineParser.class).to(PosixParser.class).in(Singleton.class);
    }

    @Provides
    Options provideOptions() {
        Options options = new Options();
        for (OptionType type : values()) {
            options.addOption(type.getOption());
        }
        return options;
    }
}
