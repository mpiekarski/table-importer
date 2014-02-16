package net.piekarski.io;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.IOException;

public class SqlInsertWriter extends AbstractTableWriter {
    public SqlInsertWriter(File file, String tableName) throws IOException {
        super(file, tableName);
    }

    @Override
    public void write(Object[] columns) throws IOException {
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)\n\n", tableName, getHeaders(), getValues(columns));
        writer.write(sql);
    }

    private String getHeaders() {
        return Joiner.on(',').join(headers);
    }

    private String getValues(Object[] columns) {
        return Joiner.on(',').join(columns);
    }
}
