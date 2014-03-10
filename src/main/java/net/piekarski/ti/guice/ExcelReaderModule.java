package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.io.reader.ExcelTableReader;
import net.piekarski.ti.io.reader.LazyTableReader;

public class ExcelReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LazyTableReader.class).to(ExcelTableReader.class).in(Singleton.class);
    }
}
