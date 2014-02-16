package net.piekarski.io;

import java.io.IOException;

public interface TableWriter {
    void setHeaders(Object[] headers);

    void write(Object[] next) throws IOException;

    void flush() throws IOException;

    void close() throws IOException;
}
