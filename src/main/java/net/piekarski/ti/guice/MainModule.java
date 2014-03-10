package net.piekarski.ti.guice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new CommandLineModule());
    }
}
