package net.piekarski.ti.io.writer;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import net.piekarski.ti.exception.WrongPrimaryKeyException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class QuotedCellsTableWriter implements StringTableWriter {
    StringTableWriter writer;

    public QuotedCellsTableWriter(StringTableWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        writer.setColumnNameList(columnNameList);
    }

    @Override
    public void writeHeader() throws IOException, XMLStreamException {
        writer.writeHeader();
    }

    @Override
    public void write(List<String> cellList) throws IOException, XMLStreamException {
        ImmutableList<String> stringCellList = FluentIterable
                .from(cellList)
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.matches("^[0-9]+$") ? input : "'" + input + "'";
                    }
                })
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
    public void openFile() throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException {
        writer.openFile();
    }
}