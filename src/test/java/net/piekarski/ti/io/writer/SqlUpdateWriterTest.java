package net.piekarski.ti.io.writer;

import com.google.common.collect.Lists;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SqlUpdateWriterTest {
    @InjectMocks
    private SqlUpdateWriter sqlUpdateWriter;

    @Mock
    private File file;

    @Mock
    private Writer writer;

    @Before
    public void setup() throws WrongPrimaryKeyException {
        sqlUpdateWriter.tableName = "tableName";
        sqlUpdateWriter.primaryKey = "ID";
        sqlUpdateWriter.setColumnNameList(Lists.newArrayList("ID", "NAME"));
        sqlUpdateWriter.writer = writer;
    }

    @Test
    public void shouldWriteHeader() throws IOException {
        // given
        // when
        sqlUpdateWriter.writeHeader();
        // then
        verify(writer).write("--liquibase formatted sql\n\n");
    }

    @Test
    public void shouldWrite() throws IOException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        sqlUpdateWriter.write(cellList);
        // then
        verify(writer).write("UPDATE tableName SET ID=1,NAME=John WHERE ID=1;\n\n");
    }

    @Ignore
    @Test
    public void shouldWriteFooter() throws IOException {
        // given
        // when
        sqlUpdateWriter.writeFooter();
        // then
    }

    @Test
    public void shouldSetColumnNameList() throws WrongPrimaryKeyException {
        // given
        ArrayList<String> columnNameList = Lists.newArrayList("NOT_PRIMARY_KEY");
        // when
        catchException(sqlUpdateWriter).setColumnNameList(columnNameList);
        // then
        assertThat(caughtException()).isInstanceOf(WrongPrimaryKeyException.class);
    }
}
