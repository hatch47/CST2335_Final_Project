package com.example.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favourites.db";
    private static final String TABLE_NAME = "favourites_table";
    private static final String COL_1 = "URL";
    private static final String COL_2 = "URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, url);
//        contentValues.put(COL_2, redirectUrl);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean deleteData(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + " = ?", new String[] { url }) > 0;
    }



}


//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    // database name and version
//    private static final String DATABASE_NAME = "favourites.db";
//    private static final int DATABASE_VERSION = 1;
//
//    // table name and column names
//    private static final String TABLE_NAME = "favourites";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_ITEM = "item";
//
//    // create table query
//    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
//            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUMN_ITEM + " TEXT"
//            + ")";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(sqLiteDatabase);
//    }
//
//    // insert item into the database
//    public boolean insertItem(String item) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ITEM, item);
//        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//        sqLiteDatabase.close();
//        return result != -1;
//    }
//
//    // retrieve all items from the database
//    public Cursor getAllItems() {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//    }
//}
