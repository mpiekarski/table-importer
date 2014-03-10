package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.piekarski.ti.Converter;

public class ConverterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Converter.class).in(Singleton.class);
    }
}
