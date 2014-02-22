package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected Writer writer;

    protected List<String> columnNameList;

    protected String tableName;

    protected AbstractTableWriter(File file, String tableName) throws IOException {
        if (file.exists()) {
            throw new FileAlreadyExistsException(file.getName(), null, "file exists");
        }
        file.createNewFile();
        writer = new PrintWriter(file);
        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    @Override
    public abstract void write(List<String> cellList) throws IOException;

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
