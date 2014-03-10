package net.piekarski.ti.io.writer;

import com.google.common.collect.Lists;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LiquibaseInsertWriterTest {
    @InjectMocks
    private LiquibaseInsertWriter liquibaseInsertWriter;

    @Mock
    private XMLStreamWriter writer;

    @Before
    public void setup() throws WrongPrimaryKeyException {
        liquibaseInsertWriter.tableName = "tableName";
        liquibaseInsertWriter.setColumnNameList(Lists.newArrayList("ID", "NAME"));
        liquibaseInsertWriter.writer = writer;
    }

    @Test
    public void shouldWriteHeader() throws XMLStreamException {
        // given
        // when
        liquibaseInsertWriter.writeHeader();
        // then
        verify(writer).writeStartDocument();
        verify(writer).writeStartElement("databaseChangeLog");
        verify(writer).writeStartElement("changeSet");
        verify(writer).writeAttribute(eq("id"), anyString());
        verify(writer).writeAttribute("author", "table-importer");
    }

    @Test
    public void shouldWrite() throws XMLStreamException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        liquibaseInsertWriter.write(cellList);
        // then
        verify(writer).writeStartElement("insert");
        verify(writer).writeAttribute("tableName", "tableName");

        verify(writer, times(2)).writeEmptyElement("column");

        verify(writer).writeAttribute("name", "ID");
        verify(writer).writeAttribute("value", "1");

        verify(writer).writeAttribute("name", "NAME");
        verify(writer).writeAttribute("value", "John");

        verify(writer).writeEndElement();
    }

    @Test
    public void shouldWriteFooter() throws XMLStreamException {
        // given
        // when
        liquibaseInsertWriter.writeFooter();
        // then
        verify(writer, times(2)).writeEndElement();
        verify(writer).writeEndDocument();
    }
}
