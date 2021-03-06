package net.piekarski.ti;

import com.google.inject.Inject;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import net.piekarski.ti.io.reader.LazyTableReader;
import net.piekarski.ti.io.writer.ObjectTableWriter;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Converter {
    private final LazyTableReader reader;
    private final ObjectTableWriter writer;

    @Inject
    public Converter(LazyTableReader reader, ObjectTableWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException, XMLStreamException, WrongPrimaryKeyException {
        try {
            tryToRun();
        } catch (Exception e) {
            writer.close();
            reader.close();
            throw e;
        }
    }

    public void tryToRun() throws IOException, XMLStreamException, WrongPrimaryKeyException {
        reader.openFile();
        writer.openFile();

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
