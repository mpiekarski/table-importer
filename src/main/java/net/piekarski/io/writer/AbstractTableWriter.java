package net.piekarski.io.writer;

import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected List<String> columnNameList;

    protected String tableName;

    protected AbstractTableWriter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
}
