package net.piekarski.ti.io.reader;

import csv.CommentCallback;
import csv.TableReader;

import java.io.File;

public abstract class AbstractTableReader<T extends TableReader> implements LazyTableReader {
    protected T reader;
    protected File file;

    public AbstractTableReader(File file) {
        this.file = file;
    }

    @Override
    public void setHasHeaderRow(boolean hasHeaderRow) {
        reader.setHasHeaderRow(hasHeaderRow);
    }

    @Override
    public boolean hasHeaderRow() {
        return reader.hasHeaderRow();
    }

    @Override
    public Object[] getHeaderRow() {
        return reader.getHeaderRow();
    }

    @Override
    public int getColumnIndex(String name) {
        return reader.getColumnIndex(name);
    }

    @Override
    public void open() {
        reader.open();
    }

    @Override
    public void reset() {
        reader.reset();
    }

    @Override
    public void close() {
        reader.close();
    }

    @Override
    public void registerCommentCallBack(CommentCallback callback) {
        reader.registerCommentCallBack(callback);
    }

    @Override
    public void unregisterCommentCallBack(CommentCallback callback) {
        reader.unregisterCommentCallBack(callback);
    }

    @Override
    public void setMinimumColumnCount(int length) {
        reader.setMinimumColumnCount(length);
    }

    @Override
    public int getMinimumColumnCount() {
        return reader.getMinimumColumnCount();
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

    @Override
    public Object[] next() {
        return reader.next();
    }

    @Override
    public void remove() {
        reader.remove();
    }
}
