package com.rahmat.app.kamus.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rahmat.app.kamus.data.db.model.Words;

import java.util.ArrayList;
import java.util.List;

public class KamusDbHelper {

    private static String DATABASE_TABLE_ID = DbContract.TABLE_NAME_ID;
    private static String DATABASE_TABLE_ENG = DbContract.TABLE_NAME_ENG;
    private String table = "";
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
    public ArrayList<Words> getByName(String query, boolean isEnglish){
        table = isEnglish ? DATABASE_TABLE_ENG : DATABASE_TABLE_ID;
        ArrayList<Words> arrayList = new ArrayList<Words>();
        Cursor cursor = database.query(table,null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        Note note;
        if (cursor.getCount()>0) {
            do {

                note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

                arrayList.add(note);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
}
