package net.piekarski.io.writer;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StringTableWriterAdapterTest {
    @InjectMocks
    private StringTableWriterAdapter stringTableWriterAdapter;

    @Mock
    private StringTableWriter stringTableWriter;

    @Test
    public void shouldSetColumnNames() {
        // given
        Object[] columnNames = new Object[]{"columnName1", "columnName2"};
        // when
        stringTableWriterAdapter.setColumnNames(columnNames);
        // then
        List<String> columnNameList = Lists.newArrayList("columnName1", "columnName2");
        verify(stringTableWriter).setColumnNameList(columnNameList);
    }

    @Test
    public void shouldWriteHeader() throws IOException, XMLStreamException {
        // given
        // when
        stringTableWriterAdapter.writeHeader();
        // then
        verify(stringTableWriter).writeHeader();
    }

    @Test
    public void shouldWrite() throws IOException, XMLStreamException {
        // given
        Object[] cells = new Object[]{"cell1", "cell2"};
        // when
        stringTableWriterAdapter.write(cells);
        // then
        List<String> cellList = Lists.newArrayList("cell1", "cell2");
        verify(stringTableWriter).write(cellList);
    }

    @Test
    public void shouldWriteFooter() throws IOException, XMLStreamException {
        // given
        // when
        stringTableWriterAdapter.writeFooter();
        // then
        verify(stringTableWriter).writeFooter();
    }

    @Test
    public void shouldFlush() throws IOException, XMLStreamException {
        // given
        // when
        stringTableWriterAdapter.flush();
        // then
        verify(stringTableWriter).flush();
    }

    @Test
    public void shouldClose() throws IOException, XMLStreamException {
        // given
        // when
        stringTableWriterAdapter.close();
        // then
        verify(stringTableWriter).close();
    }

    @Test
    public void shouldOpenFile() throws IOException, XMLStreamException {
        // given
        // when
        stringTableWriterAdapter.openFile();
        // then
        verify(stringTableWriter).openFile();
    }
}
