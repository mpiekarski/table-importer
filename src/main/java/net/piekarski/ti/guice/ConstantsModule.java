package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.guice.annotation.PrimaryKey;
import net.piekarski.ti.guice.annotation.Separator;
import net.piekarski.ti.guice.annotation.TableName;
import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;

public class ConstantsModule extends AbstractModule {
    private final CommandLine cmd;

    public ConstantsModule(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Separator
    @Singleton
    String provideSeparator() {
        String separator = OptionType.SEPARATOR.getOpt();
        return cmd.hasOption(separator) ? cmd.getOptionValue(separator) : ";";
    }

    @Provides
    @PrimaryKey
    @Singleton
    String providePrimaryKey() {
        String primaryKey = OptionType.UPDATE.getOpt();
        return cmd.getOptionValue(primaryKey);
    }

    @Provides
    @TableName
    @Singleton
    String provideTableName() {
        String tableName = OptionType.TABLE.getOpt();
        return cmd.getOptionValue(tableName);
    }
}
