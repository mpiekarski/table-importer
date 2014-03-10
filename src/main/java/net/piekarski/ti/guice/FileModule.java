package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import net.piekarski.ti.guice.annotation.InputFile;
import net.piekarski.ti.guice.annotation.OutputFile;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;

import java.io.File;

public class FileModule extends AbstractModule {
    private final CommandLine cmd;

    public FileModule(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @InputFile
    File provideInputFile() {
        String opt = OptionType.INPUT.getOpt();
        String fileName = cmd.getOptionValue(opt);
        return new File(fileName);
    }

    @Provides
    @OutputFile
    File provideOutputFile() {
        String opt = OptionType.INPUT.getOpt();
        String fileName = cmd.getOptionValue(opt);
        return new File(fileName);
    }
}
