package net.piekarski;

import csv.TableReader;
import net.piekarski.io.TableWriter;

import java.io.IOException;

public class Converter {
    private final TableReader reader;
    private final TableWriter writer;

    public Converter(TableReader reader, TableWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException {
        if (reader.hasNext()) {
            writer.setHeaders(reader.next());
        }

        while (reader.hasNext()) {
            writer.write(reader.next());
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}
