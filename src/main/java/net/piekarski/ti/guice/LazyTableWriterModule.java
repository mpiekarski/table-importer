package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.LazyTableWriter;
import net.piekarski.ti.io.writer.StringTableWriter;
import net.piekarski.ti.io.writer.StringTableWriterAdapter;

public class LazyTableWriterModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    LazyTableWriter provideLazyTableWriter(StringTableWriter writer) {
        return new StringTableWriterAdapter(writer);
    }
}
