package net.piekarski;

import csv.impl.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;

public class Generator {
    public void generate(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        CSVReader in = new CSVReader(file);

        in.setColumnSeparator(',');

        while (in.hasNext()) {
            for (Object cell : in.next()) {
                System.out.println(cell);
            }
        }

        in.close();
    }
}
