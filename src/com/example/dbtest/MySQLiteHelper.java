package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_BOOKS = "books";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_AUTHOR = "author";
	
	private static final String DATABASE_NAME = "books.db";
	private static final int DATABASE_VERSION = 1;
	
	//DATABASE CREATE STATEMENT
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS
			+ " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_TITLE + " TEXT NOT NULL, " + COLUMN_AUTHOR + " TEXT NOT NULL);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
			"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", will destroy all data.");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		onCreate(database);
	}
	
	public boolean addBook(String title, String author) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_AUTHOR, author);
		values.put(COLUMN_TITLE, title);
		if (db.insert(TABLE_BOOKS, null, values) != -1) {
			db.close();
			return true;
		}
		
		return false;
	}
	
	public Cursor getAllBooks() {
		String selectQuery = "SELECT * FROM books";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		return cursor;
	}
	
	public Cursor getBookById(int id) {
		String query = "SELECT * FROM books WHERE _id =" + id + ";";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		return cursor;
	}
	
	
	
	
	
	
	
}
