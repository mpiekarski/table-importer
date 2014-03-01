package net.piekarski.io.writer;

import net.piekarski.io.LazyFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface StringTableWriter extends LazyFile {
    void setColumnNameList(List<String> columnNameList);

    void writeHeader() throws IOException, XMLStreamException;

    void write(List<String> cellList) throws IOException, XMLStreamException;

    void writeFooter() throws IOException, XMLStreamException;

    void flush() throws IOException, XMLStreamException;

    void close() throws IOException, XMLStreamException;
}
