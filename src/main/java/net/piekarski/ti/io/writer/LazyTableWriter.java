package net.piekarski.ti.io.writer;

import net.piekarski.ti.exception.WrongPrimaryKeyException;
import net.piekarski.ti.io.LazyFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface LazyTableWriter extends LazyFile {
    void setColumnNames(Object[] columnNames) throws WrongPrimaryKeyException;

    void writeHeader() throws IOException, XMLStreamException;

    void write(Object[] cells) throws IOException, XMLStreamException;

    void writeFooter() throws IOException, XMLStreamException;

    void flush() throws IOException, XMLStreamException;

    void close() throws IOException, XMLStreamException;
}
