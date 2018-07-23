package com.rahmat.app.kamus.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    public DbHelper (Context context){
        super(context, DbContract.DB_NAME,null, DbContract.DB_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE_INDO =
            "CREATE TABLE " + DbContract.TABLE_NAME_ID + " (" +
                    DbContract.ColumnWords._ID + " INTEGER PRIMARY KEY," +
                    DbContract.ColumnWords.COLUMN_WORDS + TEXT_TYPE + COMMA_SEP +
                    DbContract.ColumnWords.COLUMN_TRANSLATE + TEXT_TYPE + " )";
    private static final String SQL_CREATE_TABLE_ENG =
            "CREATE TABLE " + DbContract.TABLE_NAME_ENG + " (" +
                    DbContract.ColumnWords._ID + " INTEGER PRIMARY KEY," +
                    DbContract.ColumnWords.COLUMN_WORDS + TEXT_TYPE + COMMA_SEP +
                    DbContract.ColumnWords.COLUMN_TRANSLATE + TEXT_TYPE + " )";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_INDO);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME_ID);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME_ENG);
        onCreate(sqLiteDatabase);
    }
}
