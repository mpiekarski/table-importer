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
public class ReplaceBadLiquibaseCharsWriterTest {
    @Mock
    private StringTableWriter stringTableWriter;

    @InjectMocks
    private ReplaceBadLiquibaseCharsWriter replaceBadLiquibaseCharsWriter = new ReplaceBadLiquibaseCharsWriter(stringTableWriter);

    @Test
    public void shouldSetColumnNameList() throws WrongPrimaryKeyException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        replaceBadLiquibaseCharsWriter.setColumnNameList(cellList);
        // then
        verify(stringTableWriter).setColumnNameList(cellList);
    }

    @Test
    public void shouldWriteHeader() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadLiquibaseCharsWriter.writeHeader();
        // then
        verify(stringTableWriter).writeHeader();
    }

    @Test
    public void shouldWrite() throws IOException, XMLStreamException {
        // given
        List<String> cellList = Lists.newArrayList("'\"$&><");
        // when
        replaceBadLiquibaseCharsWriter.write(cellList);
        // then
        verify(stringTableWriter).write(Lists.newArrayList("'&#34;&#36;&#38;&#62;&#60;"));
    }

    @Test
    public void shouldWriteFooter() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadLiquibaseCharsWriter.writeFooter();
        // then
        verify(stringTableWriter).writeFooter();
    }

    @Test
    public void shouldFlush() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadLiquibaseCharsWriter.flush();
        // then
        verify(stringTableWriter).flush();
    }

    @Test
    public void shouldClose() throws IOException, XMLStreamException {
        // given
        // when
        replaceBadLiquibaseCharsWriter.close();
        // then
        verify(stringTableWriter).close();
    }

    @Test
    public void shouldOpenFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        // given
        // when
        replaceBadLiquibaseCharsWriter.openFile();
        // then
        verify(stringTableWriter).openFile();
    }
}
