package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LiquibaseUpdateWriter extends AbstractTableWriter {
    private final String key;

    public LiquibaseUpdateWriter(File file, String tableName, String key) throws IOException {
        super(file, tableName);
        this.key = key;
    }

    @Override
    public void writeHeader() throws IOException {

    }

    @Override
    public void write(List<String> cellList) {

    }

    @Override
    public void writeFooter() throws IOException {

    }
}
