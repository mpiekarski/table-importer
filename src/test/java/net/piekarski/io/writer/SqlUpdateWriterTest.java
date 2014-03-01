package net.piekarski.io.writer;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
    public void setup() {
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
    }

    @Test
    public void shouldWrite() throws IOException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        sqlUpdateWriter.write(cellList);
        // then
        verify(writer).write("UPDATE tableName SET ID=1 AND NAME=John WHERE ID=1;\n\n");
    }

    @Test
    public void shouldWriteFooter() throws IOException {
        // given
        // when
        sqlUpdateWriter.writeFooter();
        // then
    }
}
