package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LiquibaseInsertWriter extends AbstractTableWriter {
    public LiquibaseInsertWriter(File file, String tableName) throws IOException {
        super(file, tableName);
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
