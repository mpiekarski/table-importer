package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.ReplaceBadSqlCharsWriter;
import net.piekarski.ti.io.writer.SqlUpdateWriter;
import net.piekarski.ti.io.writer.StringTableWriter;

public class SqlUpdateWriterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SqlUpdateWriter.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    StringTableWriter provideStringTableWriter(SqlUpdateWriter writer) {
        return new ReplaceBadSqlCharsWriter(writer);
    }
}
