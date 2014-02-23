package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class AbstractSqlTableWriter extends AbstractTableWriter {
    protected Writer writer;

    protected AbstractSqlTableWriter(File file, String tableName) throws IOException {
        super(tableName);
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
