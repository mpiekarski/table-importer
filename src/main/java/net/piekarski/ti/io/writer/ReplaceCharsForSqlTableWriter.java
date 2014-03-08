package net.piekarski.ti.io.writer;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import net.piekarski.ti.exception.WrongPrimaryKeyException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ReplaceCharsForSqlTableWriter implements StringTableWriter {
    StringTableWriter writer;

    public ReplaceCharsForSqlTableWriter(StringTableWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        writer.setColumnNameList(columnNameList);
    }

    @Override
    public void writeHeader() throws IOException, XMLStreamException {
        writer.writeHeader();
    }

    @Override
    public void write(List<String> cellList) throws IOException, XMLStreamException {
        ImmutableList<String> stringCellList = FluentIterable
                .from(cellList)
                .transform(ReplaceQuotes.INSTANCE)
                .transform(ReplaceDoubleQuotes.INSTANCE)
                .transform(ReplaceDolar.INSTANCE)
                .transform(ReplaceAmpersand.INSTANCE)
                .transform(QuoteNonNumericalCells.INSTANCE)
                .toList();

        writer.write(stringCellList);
    }

    @Override
    public void writeFooter() throws IOException, XMLStreamException {
        writer.writeFooter();
    }

    @Override
    public void flush() throws IOException, XMLStreamException {
        writer.flush();
    }

    @Override
    public void close() throws IOException, XMLStreamException {
        writer.close();
    }

    @Override
    public void openFile() throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException {
        writer.openFile();
    }

    private enum ReplaceQuotes implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("'", "'chr(039)'");
        }
    }

    private enum ReplaceDoubleQuotes implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("\"", "'chr(034)'");
        }
    }

    private enum ReplaceDolar implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("\\$", "'chr(036)'");
        }
    }

    private enum ReplaceAmpersand implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("&", "'chr(038)'");
        }
    }

    private enum QuoteNonNumericalCells implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.matches("^[0-9]+$") ? input : "'" + input + "'";
        }
    }
}
