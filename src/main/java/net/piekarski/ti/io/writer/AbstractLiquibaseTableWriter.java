package net.piekarski.ti.io.writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public abstract class AbstractLiquibaseTableWriter extends AbstractTableWriter {
    protected XMLStreamWriter writer;

    public AbstractLiquibaseTableWriter(File file, String tableName) {
        super(file, tableName);
    }

    @Override
    public void openFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        XMLStreamWriter xmlStreamWriter = XMLOutputFactory
                .newInstance()
                .createXMLStreamWriter(outputStreamWriter);

        IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(xmlStreamWriter);
        writer.setIndentStep("    ");

        this.writer = writer;
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
