package net.piekarski;

import csv.TableReader;
import net.piekarski.io.TableWriter;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Converter {
    private final TableReader reader;
    private final TableWriter writer;

    public Converter(TableReader reader, TableWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException, XMLStreamException {
        if (reader.hasNext()) {
            writer.setColumnNames(reader.next());
        }

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
