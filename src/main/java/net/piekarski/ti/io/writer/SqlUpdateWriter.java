package net.piekarski.ti.io.writer;

import com.google.common.base.Joiner;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import net.piekarski.ti.util.TableUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SqlUpdateWriter extends AbstractSqlTableWriter {
    private static final String SQL = "UPDATE %s SET %s WHERE %s;\n\n";
    protected String primaryKey;

    public SqlUpdateWriter(File file, String tableName, String primaryKey) {
        super(file, tableName);
        this.primaryKey = primaryKey;
    }

    @Override
    public void writeHeader() throws IOException {
        writer.write("--liquibase formatted sql\n\n");
    }

    @Override
    public void write(List<String> cellList) throws IOException {
        writer.write(getFormattedSql(cellList));
    }

    @Override
    public void writeFooter() throws IOException {
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        if (columnNameList != null && columnNameList.contains(primaryKey)) {
            super.setColumnNameList(columnNameList);
        } else {
            throw new WrongPrimaryKeyException();
        }
    }

    private String getFormattedSql(List<String> cellList) {
        return String.format(SQL, tableName, getSetStatement(cellList), getWhereStatement(cellList));
    }

    private String getWhereStatement(List<String> cellList) {
        return Joiner
                .on("=")
                .join(primaryKey, getValueForPrimaryKey(cellList));
    }

    private String getValueForPrimaryKey(List<String> cellList) {
        return cellList.get(columnNameList.indexOf(primaryKey));
    }

    private String getSetStatement(List<String> cellList) {
        return Joiner
                .on(",")
                .withKeyValueSeparator("=")
                .join(TableUtil.getMapFromLists(columnNameList, cellList));
    }
}
