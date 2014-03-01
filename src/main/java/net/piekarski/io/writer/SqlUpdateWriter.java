package net.piekarski.io.writer;

import com.google.common.base.Joiner;
import net.piekarski.util.TableUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SqlUpdateWriter extends AbstractSqlTableWriter {
    private static final String SQL = "UPDATE %s SET %s WHERE %s;\n\n";
    private final String primaryKey;

    public SqlUpdateWriter(File file, String tableName, String primaryKey) throws IOException {
        super(file, tableName);
        this.primaryKey = primaryKey;
    }

    @Override
    public void writeHeader() throws IOException {
    }

    @Override
    public void write(List<String> cellList) throws IOException {
        writer.write(getFormattedSql(cellList));
    }

    @Override
    public void writeFooter() throws IOException {
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

    public String getSetStatement(List<String> cellList) {
        return Joiner
                .on(" AND ")
                .withKeyValueSeparator("=")
                .join(TableUtil.getMapFromLists(columnNameList, cellList));
    }
}