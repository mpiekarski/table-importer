package net.piekarski;

import net.piekarski.exception.WrongPrimaryKeyException;
import net.piekarski.io.reader.LazyTableReader;
import net.piekarski.io.writer.LazyTableWriter;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Converter {
    private final LazyTableReader reader;
    private final LazyTableWriter writer;

    public Converter(LazyTableReader reader, LazyTableWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException, XMLStreamException, WrongPrimaryKeyException {
        reader.openFile();

        if (reader.hasNext()) {
            writer.setColumnNames(reader.next());
        }

        writer.openFile();
        writer.writeHeader();

        while (reader.hasNext()) {
            writer.write(reader.next());
        }

        writer.writeFooter();

        writer.flush();
        writer.close();
        reader.close();
    }
}
