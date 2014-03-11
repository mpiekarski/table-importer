package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.CommandLineService;
import net.piekarski.ti.guice.annotation.PrimaryKey;
import net.piekarski.ti.guice.annotation.Separator;
import net.piekarski.ti.guice.annotation.TableName;
import net.piekarski.ti.type.OptionType;

public class ConstantsModule extends AbstractModule {
    private final CommandLineService cmd;

    public ConstantsModule(CommandLineService cmd) {
        this.cmd = cmd;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Separator
    @Singleton
    String provideSeparator() {
        return cmd.hasOption(OptionType.SEPARATOR) ? cmd.getOptionValue(OptionType.SEPARATOR) : ";";
    }

    @Provides
    @PrimaryKey
    @Singleton
    String providePrimaryKey() {
        return cmd.getOptionValue(OptionType.UPDATE);
    }

    @Provides
    @TableName
    @Singleton
    String provideTableName() {
        return cmd.getOptionValue(OptionType.TABLE);
    }
}
