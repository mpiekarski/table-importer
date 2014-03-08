package net.piekarski.ti.io.writer;

import net.piekarski.ti.exception.WrongPrimaryKeyException;

import java.io.File;
import java.util.List;

public abstract class AbstractTableWriter implements StringTableWriter {
    protected final File file;
    protected List<String> columnNameList;
    protected String tableName;

    protected AbstractTableWriter(File file, String tableName) {
        this.file = file;
        this.tableName = tableName;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        this.columnNameList = columnNameList;
    }
}
