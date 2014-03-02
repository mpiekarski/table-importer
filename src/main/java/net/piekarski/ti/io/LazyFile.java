package net.piekarski.ti.io;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface LazyFile {
    void openFile() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException;
}
