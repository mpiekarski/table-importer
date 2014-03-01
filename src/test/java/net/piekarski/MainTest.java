package net.piekarski;

import net.piekarski.exception.TableImporterException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

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
        String[] args = new String[]{};
        when(cmd.hasHelpOption()).thenReturn(true);
        // when
        main.start(args);
        // then
        verify(cmd).parse(args);
        verify(cmd).printHelp();
    }

    @Test
    public void shouldRunConverter() throws ParseException, IOException, XMLStreamException,
            TableImporterException {
        // given
        String[] args = new String[]{};
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
