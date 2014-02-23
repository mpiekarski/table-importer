package net.piekarski.io;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LiquibaseInsertWriter extends AbstractLiquibaseTableWriter {
    public LiquibaseInsertWriter(File file, String tableName) throws XMLStreamException, IOException {
        super(file, tableName);
    }

    @Override
    public void writeHeader() throws IOException, XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("doc");
    }

    @Override
    public void write(List<String> cellList) throws XMLStreamException {
        writer.writeStartElement("person");

        writer.writeStartElement("name");
        writer.writeCharacters("some name");
        writer.writeEndElement();

        writer.writeStartElement("age");
        writer.writeCharacters("13");
        writer.writeEndElement();

        writer.writeEndElement();
    }

    @Override
    public void writeFooter() throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndDocument();
    }
}
