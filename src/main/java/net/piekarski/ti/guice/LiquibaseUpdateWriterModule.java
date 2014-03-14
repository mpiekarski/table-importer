package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.LiquibaseUpdateWriter;
import net.piekarski.ti.io.writer.ReplaceBadLiquibaseCharsWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class LiquibaseUpdateWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LiquibaseUpdateWriter.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    StringTableWriter provideStringTableWriter(LiquibaseUpdateWriter writer) {
        return new ReplaceBadLiquibaseCharsWriter(writer);
    }
}
