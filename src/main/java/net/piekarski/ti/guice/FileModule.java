package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.CommandLineService;
import net.piekarski.ti.guice.annotation.InputFile;
import net.piekarski.ti.guice.annotation.OutputFile;
import net.piekarski.ti.type.OptionType;

import java.io.File;

public class FileModule extends AbstractModule {
    private final CommandLineService cmd;

    public FileModule(CommandLineService cmd) {
        this.cmd = cmd;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @InputFile
    @Singleton
    File provideInputFile() {
        String fileName = cmd.getOptionValue(OptionType.INPUT);
        return new File(fileName);
    }

    @Provides
    @OutputFile
    @Singleton
    File provideOutputFile() {
        String fileName = cmd.getOptionValue(OptionType.OUTPUT);
        return new File(fileName);
    }
}
