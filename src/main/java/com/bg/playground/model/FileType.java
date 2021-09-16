package com.bg.playground.model;

public enum FileType {
    DOC,
    DOCX,
    PDF,
    TXT,
    RTF,
    UNSUPPORTED;

    private FileType() {
    }

    public static FileType getFileType(String fileExt) {
        if (fileExt == null) {
            return UNSUPPORTED;
        } else {
            FileType ft = null;

            try {
                ft = valueOf(fileExt.toUpperCase());
            } catch (Exception var3) {
                ft = UNSUPPORTED;
            }

            if (ft == null) {
                ft = UNSUPPORTED;
            }

            return ft;
        }
    }

    public static FileType getFileType(int type) {
        FileType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            FileType ft = var1[var3];
            if (type == ft.ordinal()) {
                return ft;
            }
        }

        return UNSUPPORTED;
    }
}