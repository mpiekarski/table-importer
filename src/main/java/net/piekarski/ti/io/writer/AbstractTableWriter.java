package net.piekarski.ti.io.writer;

import net.piekarski.ti.exception.WrongPrimaryKeyException;

import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected List<String> columnNameList;

    protected String tableName;

    protected AbstractTableWriter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        this.columnNameList = columnNameList;
    }
}
