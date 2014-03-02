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
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SqlInsertWriterTest {
    @InjectMocks
    private SqlInsertWriter sqlInsertWriter;

    @Mock
    private File file;

    @Mock
    private Writer writer;

    @Before
    public void setup() throws WrongPrimaryKeyException {
        sqlInsertWriter.tableName = "tableName";
        sqlInsertWriter.setColumnNameList(Lists.newArrayList("ID", "NAME"));
        sqlInsertWriter.writer = writer;
    }

    @Ignore
    @Test
    public void shouldWriteHeader() throws IOException {
        // given
        // when
        sqlInsertWriter.writeHeader();
        // then
    }

    @Test
    public void shouldWrite() throws IOException {
        // given
        List<String> cellList = Lists.newArrayList("1", "John");
        // when
        sqlInsertWriter.write(cellList);
        // then
        verify(writer).write("INSERT INTO tableName (ID,NAME) VALUES (1,John);\n\n");
    }

    @Ignore
    @Test
    public void shouldWriteFooter() throws IOException {
        // given
        // when
        sqlInsertWriter.writeFooter();
        // then
    }
}
