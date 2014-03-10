package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.SqlInsertWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class SqlInsertWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StringTableWriter.class).to(SqlInsertWriter.class).in(Singleton.class);
    }
}
