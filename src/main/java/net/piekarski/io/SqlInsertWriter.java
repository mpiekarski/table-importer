package net.piekarski.io;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SqlInsertWriter extends AbstractTableWriter {
    private static final String SQL = "INSERT INTO %s (%s) VALUES (%s);\n\n";

    public SqlInsertWriter(File file, String tableName) throws IOException {
        super(file, tableName);
    }

    @Override
    public void write(Object[] columns) throws IOException {
        writer.write(String.format(SQL, tableName, getHeaders(), getValues(columns)));
    }

    private String getHeaders() {
        return Joiner.on(',').join(headers);
    }

    private String getValues(Object[] columns) {
        return Joiner.on(',').join(FluentIterable.from(Arrays.asList(columns))
                .transform(Functions.toStringFunction())
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.matches("^[0-9]+$") ? input : "\"" + input + "\"";
                    }
                }));
    }
}
