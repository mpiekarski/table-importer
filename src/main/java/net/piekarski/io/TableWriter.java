package net.piekarski.io;

import java.io.IOException;

public interface TableWriter {
    void setColumnNames(Object[] columnNames);

    void write(Object[] cells) throws IOException;

    void flush() throws IOException;

    void close() throws IOException;
}
