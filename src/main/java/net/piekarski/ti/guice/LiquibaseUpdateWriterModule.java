package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.LiquibaseUpdateWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class LiquibaseUpdateWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StringTableWriter.class).to(LiquibaseUpdateWriter.class).in(Singleton.class);
    }
}
