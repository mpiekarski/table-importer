package net.piekarski.io.reader;

import csv.impl.ExcelReader;

import java.io.File;
import java.io.FileNotFoundException;

public class ExcelTableReader extends AbstractTableReader<ExcelReader> {
    public ExcelTableReader(File file) {
        super(file);
    }

    @Override
    public void openFile() throws FileNotFoundException {
        reader = new ExcelReader(file);
    }
}
