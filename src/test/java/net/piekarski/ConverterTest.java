package net.piekarski;

import csv.TableReader;
import net.piekarski.io.TableWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        given(reader.hasNext()).willReturn(true, true, false);
        // when
        converter.run();
        // then
        verify(reader, times(3)).hasNext();
        verify(reader, times(2)).next();
        verify(reader).close();

        verify(writer).setColumnNames(any(Object[].class));
        verify(writer).writeHeader();
        verify(writer).write(any(Object[].class));
        verify(writer).writeFooter();
        verify(writer).flush();
        verify(writer).close();
    }
}
