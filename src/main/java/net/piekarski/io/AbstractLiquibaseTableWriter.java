package net.piekarski.io;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class AbstractLiquibaseTableWriter extends AbstractTableWriter {

    protected final XMLStreamWriter writer;

    public AbstractLiquibaseTableWriter(File file, String tableName) throws XMLStreamException, IOException {
        super(file, tableName);

        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        XMLStreamWriter xmlStreamWriter = XMLOutputFactory
                .newInstance()
                .createXMLStreamWriter(outputStreamWriter);

        writer = new IndentingXMLStreamWriter(xmlStreamWriter);
    }

    @Override
    public void flush() throws XMLStreamException {
        writer.flush();
    }

    @Override
    public void close() throws XMLStreamException {
        writer.close();
    }
}
