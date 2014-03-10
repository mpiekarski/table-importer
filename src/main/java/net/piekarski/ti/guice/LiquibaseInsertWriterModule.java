package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.LiquibaseInsertWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class LiquibaseInsertWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StringTableWriter.class).to(LiquibaseInsertWriter.class).in(Singleton.class);
    }
}
