package net.piekarski.io;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface TableWriter {
    void setColumnNames(Object[] columnNames);

    void writeHeader() throws IOException, XMLStreamException;

    void write(Object[] cells) throws IOException, XMLStreamException;

    void writeFooter() throws IOException, XMLStreamException;

    void flush() throws IOException, XMLStreamException;

    void close() throws IOException, XMLStreamException;
}
