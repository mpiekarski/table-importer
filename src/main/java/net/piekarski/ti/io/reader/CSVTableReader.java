package net.piekarski.ti.io.reader;

import com.google.inject.Inject;
import csv.impl.CSVReader;
import net.piekarski.ti.guice.annotation.InputFile;

import java.io.File;
import java.io.FileNotFoundException;

public class CSVTableReader extends AbstractTableReader<CSVReader> {
    @Inject
    @InputFile
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
