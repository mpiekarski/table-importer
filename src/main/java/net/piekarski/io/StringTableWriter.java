package net.piekarski.io;

import java.io.IOException;
import java.util.List;

public interface StringTableWriter {
    void setColumnNames(List<String> columnNames);

    void write(List<String> cellList) throws IOException;

    void flush() throws IOException;

    void close() throws IOException;
}
