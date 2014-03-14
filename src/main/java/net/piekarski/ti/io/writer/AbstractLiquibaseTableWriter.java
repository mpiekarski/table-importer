package net.piekarski.ti.io.writer;

import javanet.staxutils.IndentingXMLStreamWriter;
import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.io.EscapingWriterFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public abstract class AbstractLiquibaseTableWriter extends AbstractTableWriter {
    protected XMLStreamWriter writer;

    public AbstractLiquibaseTableWriter(File file, String tableName) {
        super(file, tableName);
    }

    @Override
    public void openFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory2.newInstance();
        xmlOutputFactory.setProperty(XMLOutputFactory2.P_ATTR_VALUE_ESCAPER, new NoEscapingWriterFactory());

        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStreamWriter);

        IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(xmlStreamWriter);
        writer.setIndent("    ");

        this.writer = writer;
    }

    @Override
    public void flush() throws XMLStreamException {
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void close() throws XMLStreamException {
        if (writer != null) {
            writer.close();
        }
    }

    private static class NoEscapingWriterFactory implements EscapingWriterFactory {
        @Override
        public Writer createEscapingWriterFor(Writer w, String enc) throws UnsupportedEncodingException {
            return new PrintWriter(w);
        }

        @Override
        public Writer createEscapingWriterFor(OutputStream out, String enc) throws UnsupportedEncodingException {
            return new OutputStreamWriter(out, enc);
        }
    }
}
