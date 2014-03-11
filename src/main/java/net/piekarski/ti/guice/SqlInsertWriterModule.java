package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.ReplaceCharsForSqlTableWriter;
import net.piekarski.ti.io.writer.SqlInsertWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class SqlInsertWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SqlInsertWriter.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    StringTableWriter provideLazyTableWriter(SqlInsertWriter writer) {
        return new ReplaceCharsForSqlTableWriter(writer);
    }
}
