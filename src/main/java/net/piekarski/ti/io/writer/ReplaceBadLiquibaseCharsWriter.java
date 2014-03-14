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

public class ReplaceBadLiquibaseCharsWriter implements StringTableWriter {
    StringTableWriter writer;

    public ReplaceBadLiquibaseCharsWriter(StringTableWriter writer) {
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
                .transform(ReplaceAmpersand.INSTANCE)
                .transform(ReplaceDoubleQuotes.INSTANCE)
                .transform(ReplaceDolar.INSTANCE)
                .transform(ReplaceNewLine.INSTANCE)
                .transform(ReplaceGraterThan.INSTANCE)
                .transform(ReplaceLowerThan.INSTANCE)
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

    private enum ReplaceDoubleQuotes implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("\"", "&#34;");
        }
    }

    private enum ReplaceDolar implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("\\$", "&#36;");
        }
    }

    private enum ReplaceAmpersand implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("&", "&#38;");
        }
    }

    private enum ReplaceNewLine implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("\n", "\n&#10;");
        }
    }

    private enum ReplaceGraterThan implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll(">", "&#62;");
        }
    }

    private enum ReplaceLowerThan implements Function<String, String> {
        INSTANCE;

        @Override
        public String apply(String input) {
            return input.replaceAll("<", "&#60;");
        }
    }
}
