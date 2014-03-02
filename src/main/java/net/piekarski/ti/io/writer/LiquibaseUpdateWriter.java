package net.piekarski.ti.io.writer;

import net.piekarski.ti.Main;
import net.piekarski.ti.exception.WrongPrimaryKeyException;
import net.piekarski.ti.util.TableUtil;
import org.joda.time.DateTime;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LiquibaseUpdateWriter extends AbstractLiquibaseTableWriter {
    protected String primaryKey;

    public LiquibaseUpdateWriter(File file, String tableName, String primaryKey) throws IOException, XMLStreamException {
        super(file, tableName);
        this.primaryKey = primaryKey;
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

        writer.writeStartElement("update");
        writer.writeAttribute("tableName", tableName);

        for (String key : columnMap.keySet()) {
            writer.writeEmptyElement("column");
            writer.writeAttribute("name", key);
            writer.writeAttribute("value", columnMap.get(key));
        }

        writer.writeStartElement("where");
        writer.writeCharacters(primaryKey + "=" + getValueForPrimaryKey(cellList));
        writer.writeEndElement();

        writer.writeEndElement();
    }

    @Override
    public void writeFooter() throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
    }

    @Override
    public void setColumnNameList(List<String> columnNameList) throws WrongPrimaryKeyException {
        if (columnNameList != null && columnNameList.contains(primaryKey)) {
            super.setColumnNameList(columnNameList);
        } else {
            throw new WrongPrimaryKeyException();
        }
    }

    private String getValueForPrimaryKey(List<String> cellList) {
        return cellList.get(columnNameList.indexOf(primaryKey));
    }
}
