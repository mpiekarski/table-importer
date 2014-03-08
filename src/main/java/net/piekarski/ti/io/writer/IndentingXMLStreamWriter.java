package net.piekarski.ti.io.writer;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class IndentingXMLStreamWriter implements XMLStreamWriter {
    private XMLStreamWriter xmlStreamWriter;
    private String indentStep = "    ";
    private int level = 0;
    private ElementType previousElement;

    public IndentingXMLStreamWriter(XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    public void setIndentStep(String indentStep) {
        this.indentStep = indentStep;
    }

    @Override
    public void writeStartElement(String localName) throws XMLStreamException {
        indentAndIncreaseLevel();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartElement(localName);
    }

    @Override
    public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
        indentAndIncreaseLevel();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartElement(namespaceURI, localName);
    }

    @Override
    public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        indentAndIncreaseLevel();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String namespaceURI, String localName) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEmptyElement(namespaceURI, localName);
    }

    @Override
    public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEmptyElement(prefix, localName, namespaceURI);
    }

    @Override
    public void writeEmptyElement(String localName) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEmptyElement(localName);
    }

    @Override
    public void writeEndElement() throws XMLStreamException {
        decreaseLevelAndIndent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEndElement();
    }

    @Override
    public void writeEndDocument() throws XMLStreamException {
        decreaseLevelAndIndent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEndDocument();
    }

    @Override
    public void close() throws XMLStreamException {
        xmlStreamWriter.close();
    }

    @Override
    public void flush() throws XMLStreamException {
        xmlStreamWriter.flush();
    }

    @Override
    public void writeAttribute(String localName, String value) throws XMLStreamException {
        xmlStreamWriter.writeAttribute(localName, value);
    }

    @Override
    public void writeAttribute(String prefix, String namespaceURI, String localName, String value) throws XMLStreamException {
        xmlStreamWriter.writeAttribute(prefix, namespaceURI, localName, value);
    }

    @Override
    public void writeAttribute(String namespaceURI, String localName, String value) throws XMLStreamException {
        xmlStreamWriter.writeAttribute(namespaceURI, localName, value);
    }

    @Override
    public void writeNamespace(String prefix, String namespaceURI) throws XMLStreamException {
        xmlStreamWriter.writeNamespace(prefix, namespaceURI);
    }

    @Override
    public void writeDefaultNamespace(String namespaceURI) throws XMLStreamException {
        xmlStreamWriter.writeDefaultNamespace(namespaceURI);
    }

    @Override
    public void writeComment(String data) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeComment(data);
    }

    @Override
    public void writeProcessingInstruction(String target) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeProcessingInstruction(target);
    }

    @Override
    public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeProcessingInstruction(target, data);
    }

    @Override
    public void writeCData(String data) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeCData(data);
    }

    @Override
    public void writeDTD(String dtd) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeDTD(dtd);
    }

    @Override
    public void writeEntityRef(String name) throws XMLStreamException {
        indent();
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeEntityRef(name);
    }

    @Override
    public void writeStartDocument() throws XMLStreamException {
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartDocument();
    }

    @Override
    public void writeStartDocument(String version) throws XMLStreamException {
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartDocument(version);
    }

    @Override
    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        previousElement = ElementType.ELEMENT;
        xmlStreamWriter.writeStartDocument(encoding, version);
    }

    @Override
    public void writeCharacters(String text) throws XMLStreamException {
        previousElement = ElementType.TEXT;
        xmlStreamWriter.writeCharacters(text);
    }

    @Override
    public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
        previousElement = ElementType.TEXT;
        xmlStreamWriter.writeCharacters(text, start, len);
    }

    @Override
    public String getPrefix(String uri) throws XMLStreamException {
        return xmlStreamWriter.getPrefix(uri);
    }

    @Override
    public void setPrefix(String prefix, String uri) throws XMLStreamException {
        xmlStreamWriter.setPrefix(prefix, uri);
    }

    @Override
    public void setDefaultNamespace(String uri) throws XMLStreamException {
        xmlStreamWriter.setDefaultNamespace(uri);
    }

    @Override
    public void setNamespaceContext(NamespaceContext context) throws XMLStreamException {
        xmlStreamWriter.setNamespaceContext(context);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return xmlStreamWriter.getNamespaceContext();
    }

    @Override
    public Object getProperty(String name) throws IllegalArgumentException {
        return xmlStreamWriter.getProperty(name);
    }

    private void indent() throws XMLStreamException {
        xmlStreamWriter.writeCharacters(getIndent());
    }

    private void indentAndIncreaseLevel() throws XMLStreamException {
        indent();
        level += 1;
    }

    private void decreaseLevelAndIndent() throws XMLStreamException {
        level -= 1;
        if (previousElement == ElementType.ELEMENT) {
            indent();
        }
    }

    private String getIndent() {
        StringBuilder indent = new StringBuilder("\n");
        for (int i = 0; i < level; i++) {
            indent.append(indentStep);
        }
        return indent.toString();
    }

    private static enum ElementType {
        TEXT, ELEMENT
    }
}
