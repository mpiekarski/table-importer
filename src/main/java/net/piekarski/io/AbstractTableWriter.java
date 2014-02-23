package net.piekarski.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected List<String> columnNameList;

    protected String tableName;

    protected AbstractTableWriter(File file, String tableName) throws IOException {
        if (file.exists()) {
            throw new FileAlreadyExistsException(file.getName(), null, "file exists");
        }
        file.createNewFile();

        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
}
