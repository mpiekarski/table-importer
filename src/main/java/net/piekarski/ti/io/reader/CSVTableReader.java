package net.piekarski.ti.io.reader;

import com.google.inject.Inject;
import csv.impl.CSVReader;
import net.piekarski.ti.guice.annotation.InputFile;
import net.piekarski.ti.guice.annotation.Separator;

import java.io.File;
import java.io.FileNotFoundException;

public class CSVTableReader extends AbstractTableReader<CSVReader> {
    private final String separator;

    @Inject
    @InputFile
    @Separator
    public CSVTableReader(File file, String separator) {
        super(file);
        this.separator = separator;
    }

    @Override
    public void openFile() throws FileNotFoundException {
        reader = new CSVReader(file);
        reader.setColumnSeparator(separator.charAt(0));
    }
}
