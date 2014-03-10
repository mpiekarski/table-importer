package net.piekarski.ti.io.writer;

import com.google.inject.Inject;
import net.piekarski.ti.guice.annotation.InputFile;
import net.piekarski.ti.guice.annotation.TableName;
import net.piekarski.ti.util.TableUtil;
import org.joda.time.DateTime;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.List;
import java.util.Map;

public class LiquibaseInsertWriter extends AbstractLiquibaseTableWriter {
    @Inject
    public LiquibaseInsertWriter(@InputFile File file, @TableName String tableName) {
        super(file, tableName);
    }

    @Override
    public void writeHeader() throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("databaseChangeLog");
        writer.writeStartElement("changeSet");
        writer.writeAttribute("id", String.valueOf(DateTime.now().getMillis()));
        writer.writeAttribute("author", "table-importer");
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
