package net.piekarski;

import net.piekarski.exception.CommandLineNotParsedException;
import net.piekarski.exception.FileFormatNotSupportedException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    @InjectMocks
    private Main main;

    @Mock
    private CommandLineService cmd;

    @Test
    public void shouldPrintHelp() throws ParseException {
        // given
        String[] args = null;
        when(cmd.hasHelpOption()).thenReturn(true);
        // when
        main.start(args);
        // then
        verify(cmd).parse(args);
        verify(cmd).printHelp();
    }

    @Test
    public void shouldRunConverter() throws ParseException, FileNotFoundException, FileFormatNotSupportedException, CommandLineNotParsedException {
        // given
        String[] args = null;
        Converter converter = mock(Converter.class);
        when(cmd.getConverter()).thenReturn(converter);
        // when
        main.start(args);
        // then
        verify(cmd).parse(args);
        verify(cmd).getConverter();
        verify(converter).run();
    }
}
