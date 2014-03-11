package net.piekarski.ti;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        main.run(args);
        // then
        verify(cmd).parse(args);
        verify(cmd).printHelp();
    }
}
