package net.piekarski.io;

import java.io.IOException;
import java.util.List;

public interface StringTableWriter {
    void setColumnNameList(List<String> columnNameList);

    void writeHeader() throws IOException;

    void write(List<String> cellList) throws IOException;

    void writeFooter() throws IOException;

    void flush() throws IOException;

    void close() throws IOException;
}
