package net.piekarski.io;

import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TableWriterAdapter implements TableWriter {
    StringTableWriter writer;

    public TableWriterAdapter(StringTableWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setColumnNames(Object[] columnNames) {
        List<Object> headerList = Arrays.asList(columnNames);

        ImmutableList<String> columnNameList = FluentIterable
                .from(headerList)
                .transform(Functions.toStringFunction())
                .toList();

        writer.setColumnNames(columnNameList);
    }

    @Override
    public void write(Object[] cells) throws IOException {
        List<Object> cellList = Arrays.asList(cells);

        ImmutableList<String> stringCellList = FluentIterable
                .from(cellList)
                .transform(Functions.toStringFunction())
                .toList();

        writer.write(stringCellList);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
