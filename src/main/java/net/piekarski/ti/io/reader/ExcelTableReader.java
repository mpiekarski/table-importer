package net.piekarski.ti.io.reader;

import com.google.inject.Inject;
import csv.impl.ExcelReader;
import net.piekarski.ti.guice.annotation.InputFile;

import java.io.File;
import java.io.FileNotFoundException;

public class ExcelTableReader extends AbstractTableReader<ExcelReader> {
    @Inject
    public ExcelTableReader(@InputFile File file) {
        super(file);
    }

    @Override
    public void openFile() throws FileNotFoundException {
        reader = new ExcelReader(file);
    }
}
