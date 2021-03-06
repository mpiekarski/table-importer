package net.piekarski.ti.io.writer;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import net.piekarski.ti.guice.annotation.OutputFile;
import net.piekarski.ti.guice.annotation.TableName;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SqlInsertWriter extends AbstractSqlTableWriter {
    private static final String SQL = "INSERT INTO %s (%s) VALUES (%s);\n\n";

    @Inject
    public SqlInsertWriter(@OutputFile File file, @TableName String tableName) {
        super(file, tableName);
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

    private String getFormattedSql(List<String> cellList) {
        return String.format(SQL, tableName, getColumnNames(), getValues(cellList));
    }

    private String getColumnNames() {
        return Joiner.on(',').join(columnNameList);
    }

    private String getValues(List<String> columns) {
        return Joiner.on(',').join(columns);
    }
}
