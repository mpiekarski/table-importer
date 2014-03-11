package net.piekarski.ti;

import net.piekarski.ti.type.OptionType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineServiceTest {
    @InjectMocks
    private CommandLineService commandLineService;

    @Mock
    private Options options;

    @Mock
    private HelpFormatter helpFormatter;

    @Mock
    private CommandLineParser parser;

    @Mock
    private CommandLine cmd;

    @Before
    public void setup() {
        commandLineService.cmd = cmd;
    }

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
        verify(helpFormatter).printHelp("table-importer", commandLineService.options);
    }

    @Test
    public void shouldCheckIfHasHelpOption() {
        // given
        // when
        commandLineService.hasHelpOption();
        // then
        verify(cmd).hasOption(OptionType.HELP.getOpt());
    }

    @Test
    public void shouldCheckIfHasOption() {
        // given
        // when
        commandLineService.hasOption(OptionType.UPDATE);
        // then
        verify(cmd).hasOption(OptionType.UPDATE.getOpt());
    }

    @Test
    public void shouldCheckIfHasOptionWithCommandlineNotParsed() {
        // given
        // when
        boolean actual = commandLineService.hasOption(OptionType.UPDATE);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldGetOptionValue() {
        // given
        // when
        commandLineService.getOptionValue(OptionType.UPDATE);
        // then
        verify(cmd).getOptionValue(OptionType.UPDATE.getOpt());
    }

    @Test
    public void shouldGetOptionValueWithCommandLineNotParsed() {
        // given
        // when
        String actual = commandLineService.getOptionValue(OptionType.UPDATE);
        // then
        assertThat(actual).isNull();
    }
}
