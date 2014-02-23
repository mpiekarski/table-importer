package net.piekarski.io;

import java.io.IOException;
import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected List<String> columnNameList;

    protected String tableName;

    protected AbstractTableWriter(String tableName) throws IOException {
        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
}
