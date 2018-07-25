package com.rahmat.app.kamus.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.rahmat.app.kamus.data.db.model.Words;

import java.util.ArrayList;
import java.util.List;

public class KamusDbHelper {

    private static String DATABASE_TABLE_ID = DbContract.TABLE_NAME_ID;
    private static String DATABASE_TABLE_ENG = DbContract.TABLE_NAME_ENG;
    private Context context;
    private DbHelper dbHelper;

    private SQLiteDatabase database;

    public KamusDbHelper(Context context){
        this.context = context;
    }

    public KamusDbHelper open() throws SQLException {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(Words words, boolean isEnglish){
        String TABLE = isEnglish ? DATABASE_TABLE_ENG : DATABASE_TABLE_ID;


        String q = "INSERT INTO " + TABLE + " (" +
                DbContract.ColumnWords.COLUMN_WORDS + ", " +
                DbContract.ColumnWords.COLUMN_TRANSLATE + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(q);

        stmt.bindString(1, words.getWords());
        stmt.bindString(2, words.getTranslation());
        stmt.execute();
        stmt.clearBindings();

        database.setTransactionSuccessful();
        database.endTransaction();

    }

    public List<Words> getAll(boolean isEnglish){
        String TABLE = isEnglish ? DATABASE_TABLE_ENG : DATABASE_TABLE_ID;
        List<Words> wordsList = new ArrayList<>();

        String q = "SELECT * FROM"+ TABLE +" ORDER BY " + DbContract.ColumnWords.COLUMN_WORDS+" ASC";

        Cursor cursor = database.rawQuery(q, null);

        if(cursor.moveToFirst()){
            do{
                Words words = new Words();
                words.setId(cursor.getInt(cursor.getColumnIndex(DbContract.ColumnWords._ID)));
                words.setWords(cursor.getString(cursor.getColumnIndex(DbContract.ColumnWords.COLUMN_WORDS)));
                words.setTranslation(cursor.getString(cursor.getColumnIndex(DbContract.ColumnWords.COLUMN_TRANSLATE)));
                wordsList.add(words);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return wordsList;
    }
    public List<Words> getByName(String query, boolean isEnglish){
        String TABLE = isEnglish ? DATABASE_TABLE_ENG : DATABASE_TABLE_ID;
        List<Words> wordsList = new ArrayList<Words>();

        String q = "SELECT * FROM"+ TABLE +" WHERE " + DbContract.ColumnWords.COLUMN_WORDS
                +" LIKE '%" +query.trim()+ " '%";

        Cursor cursor = database.rawQuery(q, null);

        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            do{
                Words words = new Words();
                words.setId(cursor.getInt(cursor.getColumnIndex(DbContract.ColumnWords._ID)));
                words.setWords(cursor.getString(cursor.getColumnIndex(DbContract.ColumnWords.COLUMN_WORDS)));
                words.setTranslation(cursor.getString(cursor.getColumnIndex(DbContract.ColumnWords.COLUMN_TRANSLATE)));
                wordsList.add(words);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return wordsList;
    }
}
