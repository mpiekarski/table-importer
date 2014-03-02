package net.piekarski.ti.io.writer;

import net.piekarski.ti.Main;
import net.piekarski.ti.util.TableUtil;
import org.joda.time.DateTime;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LiquibaseInsertWriter extends AbstractLiquibaseTableWriter {
    public LiquibaseInsertWriter(File file, String tableName) throws XMLStreamException, IOException {
        super(file, tableName);
    }

    @Override
    public void writeHeader() throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("databaseChangeLog");
        writer.writeStartElement("changeSet");
        writer.writeAttribute("id", String.valueOf(DateTime.now().getMillis()));
        writer.writeAttribute("author", Main.APPLICATION_NAME);
    }

    @Override
    public void write(List<String> cellList) throws XMLStreamException {
        Map<String, String> columnMap = TableUtil.getMapFromLists(columnNameList, cellList);

        writer.writeStartElement("insert");
        writer.writeAttribute("tableName", tableName);

        for (String key : columnMap.keySet()) {
            writer.writeEmptyElement("column");
            writer.writeAttribute("name", key);
            writer.writeAttribute("value", columnMap.get(key));
        }

        writer.writeEndElement();
    }

    @Override
    public void writeFooter() throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
    }
}
