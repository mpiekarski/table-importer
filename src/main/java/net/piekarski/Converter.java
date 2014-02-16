package net.piekarski;

import csv.TableReader;
import net.piekarski.io.TableWriter;

public class Converter {
    private final TableReader reader;
    private final TableWriter writer;

    public Converter(TableReader reader, TableWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() {
        while (reader.hasNext()) {
            for (Object cell : reader.next()) {
                System.out.println(cell);
            }
        }
        reader.close();
    }
}
