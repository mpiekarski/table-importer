package net.piekarski.io;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SqlInsertWriter extends AbstractTableWriter {
    private static final String SQL = "INSERT INTO %s (%s) VALUES (%s);\n\n";

    public SqlInsertWriter(File file, String tableName) throws IOException {
        super(file, tableName);
    }

    @Override
    public void write(List<String> columns) throws IOException {
        writer.write(String.format(SQL, tableName, getHeaders(), getValues(columns)));
    }

    private String getHeaders() {
        return Joiner.on(',').join(columnNames);
    }

    private String getValues(List<String> columns) {
        return Joiner.on(',').join(columns);
    }
}
