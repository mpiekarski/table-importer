package net.piekarski.io.writer;

import net.piekarski.io.LazyFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface LazyTableWriter extends LazyFile {
    void setColumnNames(Object[] columnNames);

    void writeHeader() throws IOException, XMLStreamException;

    void write(Object[] cells) throws IOException, XMLStreamException;

    void writeFooter() throws IOException, XMLStreamException;

    void flush() throws IOException, XMLStreamException;

    void close() throws IOException, XMLStreamException;
}
