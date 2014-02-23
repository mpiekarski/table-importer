package net.piekarski;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CommandLineServiceTest {
    @InjectMocks
    private CommandLineService commandLineService;

    @Mock
    private HelpFormatter helpFormatter;

    @Mock
    private CommandLineParser parser;

    @Test
    public void shouldParse() throws ParseException {
        // given
        String[] args = new String[]{};
        // when
        commandLineService.parse(args);
        // then
        verify(parser).parse(commandLineService.options, args);
    }

    @Test
    public void shouldPrintHelp() {
        // given
        // when
        commandLineService.printHelp();
        // then
        verify(helpFormatter).printHelp(Main.APPLICATION_NAME, commandLineService.options);
    }

    @Test
    public void shouldHasHelpOption() {
        // given
        // when
        commandLineService.hasHelpOption();
        // then
        verify(helpFormatter).printHelp(Main.APPLICATION_NAME, commandLineService.options);
    }
}
