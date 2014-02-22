package net.piekarski.io;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
        writer.write(String.format(SQL, tableName, getUpdateString(cellList), getKeyString(cellList)));
    }

    private String getKeyString(List<String> cellList) {
        int indexOfKey = columnNames.indexOf(key);
        return key + "=" + cellList.get(indexOfKey);
    }

    public String getUpdateString(List<String> cellList) {
        final Iterator<String> values = cellList.iterator();

        return Joiner.on(" AND ").join(FluentIterable.from(columnNames)
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input + "=" + values.next();
                    }
                }));
    }

}