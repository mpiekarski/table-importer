package net.piekarski.io;

import java.io.IOException;

public interface TableWriter {
    void setColumnNames(Object[] columnNames);

    void writeHeader() throws IOException;

    void write(Object[] cells) throws IOException;

    void writeFooter() throws IOException;

    void flush() throws IOException;

    void close() throws IOException;
}
