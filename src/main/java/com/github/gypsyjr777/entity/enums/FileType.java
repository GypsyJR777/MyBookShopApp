package com.github.gypsyjr777.entity.enums;

public enum FileType {
    PDF(".pdf"), EPUB(".epub"), FB2(".fb2");

    private final String fileExtensionString;

    FileType(String fileExtensionString) {
        this.fileExtensionString = fileExtensionString;
    }

    public static String getExtensionStringByTypeId(Integer typeId) {
        return switch (typeId) {
            case 1 -> PDF.fileExtensionString;
            case 2 -> EPUB.fileExtensionString;
            case 3 -> FB2.fileExtensionString;
            default -> "";
        };
    }
}
