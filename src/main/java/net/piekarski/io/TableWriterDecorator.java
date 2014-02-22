package net.piekarski.io;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.List;

public class TableWriterDecorator implements StringTableWriter {
    StringTableWriter writer;

    public TableWriterDecorator(StringTableWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setColumnNames(List<String> columnNames) {
        writer.setColumnNames(columnNames);
    }

    @Override
    public void write(List<String> cellList) throws IOException {
        ImmutableList<String> stringCellList = FluentIterable
                .from(cellList)
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.matches("^[0-9]+$") ? input : "\"" + input + "\"";
                    }
                })
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
