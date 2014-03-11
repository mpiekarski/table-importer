package net.piekarski.ti.io.writer;

import com.google.common.collect.Lists;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QuotedCellsTableWriterTest {
    @InjectMocks
    private ReplaceBadSqlCharsWriter quotedCellsTableWriter;

    @Mock
    private StringTableWriter stringTableWriter;

    @Test
    public void shouldSetColumnNameList() throws WrongPrimaryKeyException {
        // given
        List<String> columnNameList = Lists.newArrayList("ID", "NAME");
        // when
        quotedCellsTableWriter.setColumnNameList(columnNameList);
        // then
        verify(stringTableWriter).setColumnNameList(columnNameList);
    }

    @Test
    public void shouldWriteHeader() throws IOException, XMLStreamException {
        // given
        // when
        quotedCellsTableWriter.writeHeader();
        // then
        verify(stringTableWriter).writeHeader();
    }

    @Test
    public void shouldWrite() throws IOException, XMLStreamException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        List<String> quotedCellList = Lists.newArrayList("1", "'John'");
        // when
        quotedCellsTableWriter.write(cellList);
        // then
        verify(stringTableWriter).write(quotedCellList);
    }

    @Test
    public void shouldWriteFooter() throws IOException, XMLStreamException {
        // given
        // when
        quotedCellsTableWriter.writeFooter();
        // then
        verify(stringTableWriter).writeFooter();
    }

    @Test
    public void shouldFlush() throws IOException, XMLStreamException {
        // given
        // when
        quotedCellsTableWriter.flush();
        // then
        verify(stringTableWriter).flush();
    }

    @Test
    public void shouldClose() throws IOException, XMLStreamException {
        // given
        // when
        quotedCellsTableWriter.close();
        // then
        verify(stringTableWriter).close();
    }

    @Test
    public void shouldOpenFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        // given
        // when
        quotedCellsTableWriter.openFile();
        // then
        verify(stringTableWriter).openFile();
    }
}
