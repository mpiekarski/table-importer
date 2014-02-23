package net.piekarski.io;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface StringTableWriter {
    void setColumnNameList(List<String> columnNameList);

    void writeHeader() throws IOException, XMLStreamException;

    void write(List<String> cellList) throws IOException, XMLStreamException;

    void writeFooter() throws IOException, XMLStreamException;

    void flush() throws IOException, XMLStreamException;

    void close() throws IOException, XMLStreamException;
}
