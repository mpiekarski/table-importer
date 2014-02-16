package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;

public abstract class AbstractTableWriter implements TableWriter {
    protected Writer writer;

    protected Object[] headers;

    protected String tableName;

    protected AbstractTableWriter(File file, String tableName) throws IOException {
        if (file.exists()) {
            throw new FileAlreadyExistsException(file.getName(), null, "file exists");
        }
        file.createNewFile();
        writer = new PrintWriter(file);
        this.tableName = tableName;
    }

    public void setHeaders(Object[] headers) {
        this.headers = headers;
    }

    @Override
    public abstract void write(Object[] next) throws IOException;

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
