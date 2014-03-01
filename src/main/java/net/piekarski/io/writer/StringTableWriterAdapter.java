package net.piekarski.io.writer;

import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class StringTableWriterAdapter implements LazyTableWriter {
    StringTableWriter writer;

    public StringTableWriterAdapter(StringTableWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setColumnNames(Object[] columnNames) {
        List<Object> headerList = Arrays.asList(columnNames);

        ImmutableList<String> columnNameList = FluentIterable
                .from(headerList)
                .transform(Functions.toStringFunction())
                .toList();

        writer.setColumnNameList(columnNameList);
    }

    @Override
    public void writeHeader() throws IOException, XMLStreamException {
        writer.writeHeader();
    }

    @Override
    public void write(Object[] cells) throws IOException, XMLStreamException {
        List<Object> cellList = Arrays.asList(cells);

        ImmutableList<String> stringCellList = FluentIterable
                .from(cellList)
                .transform(Functions.toStringFunction())
                .toList();

        writer.write(stringCellList);
    }

    @Override
    public void writeFooter() throws IOException, XMLStreamException {
        writer.writeFooter();
    }

    @Override
    public void flush() throws IOException, XMLStreamException {
        writer.flush();
    }

    @Override
    public void close() throws IOException, XMLStreamException {
        writer.close();
    }

    @Override
    public void openFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        writer.openFile();
    }
}
