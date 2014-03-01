package net.piekarski.io.reader;

import csv.impl.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;

public class CSVTableReader extends AbstractTableReader<CSVReader> {
    public CSVTableReader(File file) {
        super(file);
    }

    @Override
    public void openFile() throws FileNotFoundException {
        reader = new CSVReader(file);
    }

    public void setColumnSeparator(char c) {
        reader.setColumnSeparator(c);
    }
}
