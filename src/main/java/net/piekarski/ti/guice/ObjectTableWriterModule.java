package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.piekarski.ti.io.writer.ObjectTableWriter;
import net.piekarski.ti.io.writer.StringTableWriter;
import net.piekarski.ti.io.writer.StringTableWriterAdapter;

public class ObjectTableWriterModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    ObjectTableWriter provideObjectTableWriter(StringTableWriter writer) {
        return new StringTableWriterAdapter(writer);
    }
}
