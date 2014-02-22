package net.piekarski.io;

import com.google.common.base.Joiner;
import net.piekarski.util.TableUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SqlUpdateWriter extends AbstractTableWriter {
    private static final String SQL = "UPDATE %s SET %s WHERE %s;\n\n";
    private final String key;

    public SqlUpdateWriter(File file, String tableName, String key) throws IOException {
        super(file, tableName);
        this.key = key;
    }

    @Override
    public void write(List<String> cellList) throws IOException {
        writer.write(getFormattedSql(cellList));
    }

    private String getFormattedSql(List<String> cellList) {
        return String.format(SQL, tableName, getUpdateString(cellList), getKeyString(cellList));
    }

    private String getKeyString(List<String> cellList) {
        return Joiner
                .on("=")
                .join(key, getValueForKey(cellList));
    }

    private String getValueForKey(List<String> cellList) {
        return cellList.get(columnNames.indexOf(key));
    }

    public String getUpdateString(List<String> cellList) {
        return Joiner
                .on(" AND ")
                .withKeyValueSeparator("=")
                .join(TableUtil.getMapFromLists(columnNames, cellList));
    }
}
