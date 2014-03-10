package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.io.reader.CSVTableReader;
import net.piekarski.ti.io.reader.LazyTableReader;

public class CSVReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LazyTableReader.class).to(CSVTableReader.class).in(Singleton.class);
    }
}
