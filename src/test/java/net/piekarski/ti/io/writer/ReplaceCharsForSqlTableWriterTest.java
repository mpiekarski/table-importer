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
public class ReplaceCharsForSqlTableWriterTest {
    @Mock
    private StringTableWriter stringTableWriter;

    @InjectMocks
    private ReplaceBadSqlCharsWriter replaceBadSqlCharsWriter = new ReplaceBadSqlCharsWriter(stringTableWriter);

    @Test
    public void shouldSetColumnNameList() throws WrongPrimaryKeyException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        replaceBadSqlCharsWriter.setColumnNameList(cellList);
        // then
        verify(stringTableWriter).setColumnNameList(cellList);
    }

    @Test
    public void shouldWriteHeader() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadSqlCharsWriter.writeHeader();
        // then
        verify(stringTableWriter).writeHeader();
    }

    @Test
    public void shouldWriteNonNumericCell() throws IOException, XMLStreamException {
        // given
        List<String> cellList = Lists.newArrayList("'\"$&");
        // when
        replaceBadSqlCharsWriter.write(cellList);
        // then
        verify(stringTableWriter).write(Lists.newArrayList("''chr(039)''chr(034)''chr(036)''chr(038)''"));
    }

    @Test
    public void shouldWriteNumericCell() throws IOException, XMLStreamException {
        // given
        List<String> cellList = Lists.newArrayList("1");
        // when
        replaceBadSqlCharsWriter.write(cellList);
        // then
        verify(stringTableWriter).write(Lists.newArrayList("1"));
    }

    @Test
    public void shouldWriteFooter() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadSqlCharsWriter.writeFooter();
        // then
        verify(stringTableWriter).writeFooter();
    }

    @Test
    public void shouldFlush() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadSqlCharsWriter.flush();
        // then
        verify(stringTableWriter).flush();
    }

    @Test
    public void shouldClose() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadSqlCharsWriter.close();
        // then
        verify(stringTableWriter).close();
    }

    @Test
    public void shouldOpenFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        // given
        // when
        replaceBadSqlCharsWriter.openFile();
        // then
        verify(stringTableWriter).openFile();
    }
}
