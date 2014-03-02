package net.piekarski.ti.io.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class AbstractSqlTableWriter extends AbstractTableWriter {
    private final File file;
    protected Writer writer;

    protected AbstractSqlTableWriter(File file, String tableName) {
        super(tableName);
        this.file = file;
    }

    @Override
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(file);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
