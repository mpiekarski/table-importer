package net.piekarski.io;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class SqlUpdateWriter extends AbstractTableWriter {
    private static final String SQL = "UPDATE %s SET %s WHERE %s;\n\n";
    private final String key;

    public SqlUpdateWriter(File file, String tableName, String key) throws IOException {
        super(file, tableName);
        this.key = key;
    }

    @Override
    public void write(Object[] columns) throws IOException {
        writer.write(String.format(SQL, tableName, getUpdateString(columns), getKeyString(columns)));
    }

    private String getKeyString(Object[] columns) {
        return key + "=" + columns[getKeyIndex(key)];
    }

    private int getKeyIndex(String key) {
        return FluentIterable.from(Arrays.asList(headers))
                .transform(Functions.toStringFunction())
                .toList()
                .indexOf(key);
    }

    public String getUpdateString(Object[] columns) {
        final Iterator<String> values = getValues(columns).iterator();

        return Joiner.on(" AND ").join(FluentIterable.from(Arrays.asList(headers))
                .transform(Functions.toStringFunction())
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input + "=" + values.next();
                    }
                }));
    }

    private FluentIterable<String> getValues(Object[] columns) {
        return FluentIterable.from(Arrays.asList(columns))
                .transform(Functions.toStringFunction())
                .transform(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return input.matches("^[0-9]+$") ? input : "\"" + input + "\"";
                    }
                });
    }
}
