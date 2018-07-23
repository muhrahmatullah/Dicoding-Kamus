package com.rahmat.app.kamus.data.db;

import android.provider.BaseColumns;

public class DbContract {
    private DbContract(){}

    public static final String DB_NAME = "kamus.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME_ID = "indonesia_words";
    public static final String TABLE_NAME_ENG = "english_words";

    public static class ColumnWords implements BaseColumns{

        public static final String COLUMN_WORDS = "word";
        public static final String COLUMN_TRANSLATE = "translate";
    }
}
