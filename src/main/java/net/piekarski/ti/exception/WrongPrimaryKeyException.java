package net.piekarski.ti.exception;

public class WrongPrimaryKeyException extends TableImporterException {
    public static final String FILE_FORMAT_NOT_SUPPORTED = "column name list doesn't include primary key";

    @Override
    public String getMessage() {
        return FILE_FORMAT_NOT_SUPPORTED;
    }
}
