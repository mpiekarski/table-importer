package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.guice.annotation.Separator;
import net.piekarski.ti.io.reader.CSVTableReader;
import net.piekarski.ti.io.reader.LazyTableReader;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;

public class CSVReaderModule extends AbstractModule {
    private final CommandLine cmd;

    public CSVReaderModule(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    protected void configure() {
        String separator = OptionType.SEPARATOR.getOpt();

        bind(LazyTableReader.class).to(CSVTableReader.class).in(Singleton.class);
        bindConstant().annotatedWith(Separator.class)
                .to(cmd.hasOption(separator) ? cmd.getOptionValue(separator): ",");
    }
}
