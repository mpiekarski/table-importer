package net.piekarski;

import csv.TableReader;
import csv.TableWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {
    @InjectMocks
    private Converter converter;

    @Mock
    private TableReader reader;

    @Mock
    private TableWriter writer;

    @Test
    public void shouldRun() throws IOException, XMLStreamException {
        // given
        // when
        converter.run();
        // then
    }
}
