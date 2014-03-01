package net.piekarski.exception;

public class FileFormatNotSupportedException extends TableImporterException {
    public static final String FILE_FORMAT_NOT_SUPPORTED = "file format not supported";

    @Override
    public String getMessage() {
        return FILE_FORMAT_NOT_SUPPORTED;
    }
}
