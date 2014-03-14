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
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LiquibaseUpdateWriterTest {
    @InjectMocks
    private LiquibaseUpdateWriter liquibaseUpdateWriter;

    @Mock
    private XMLStreamWriter writer;

    @Before
    public void setup() throws WrongPrimaryKeyException {
        liquibaseUpdateWriter.tableName = "tableName";
        liquibaseUpdateWriter.primaryKey = "ID";
        liquibaseUpdateWriter.setColumnNameList(Lists.newArrayList("ID", "NAME"));
        liquibaseUpdateWriter.writer = writer;
    }

    @Test
    public void shouldWriteHeader() throws XMLStreamException {
        // given
        // when
        liquibaseUpdateWriter.writeHeader();
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
        liquibaseUpdateWriter.write(cellList);
        // then
        verify(writer).writeStartElement("update");
        verify(writer).writeAttribute("tableName", "tableName");

        verify(writer).writeEmptyElement("column");

        verify(writer).writeAttribute("name", "NAME");
        verify(writer).writeAttribute("value", "John");

        verify(writer).writeStartElement("where");
        verify(writer).writeCharacters("ID=1");

        verify(writer, times(2)).writeEndElement();
    }

    @Test
    public void shouldWriteFooter() throws XMLStreamException {
        // given
        // when
        liquibaseUpdateWriter.writeFooter();
        // then
        verify(writer, times(2)).writeEndElement();
        verify(writer).writeEndDocument();
    }

    @Test
    public void shouldSetColumnNameList() throws WrongPrimaryKeyException {
        // given
        ArrayList<String> columnNameList = Lists.newArrayList("NOT_PRIMARY_KEY");
        // when
        catchException(liquibaseUpdateWriter).setColumnNameList(columnNameList);
        // then
        assertThat(caughtException()).isInstanceOf(WrongPrimaryKeyException.class);
    }
}
