package net.piekarski.io.reader;

import csv.TableReader;

import java.io.FileNotFoundException;

public interface LazyTableReader extends TableReader{
    void openFile() throws FileNotFoundException;
}
