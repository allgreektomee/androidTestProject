package com.devcation.project.post;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PostDatabase {

	/**
	 * TAG for debugging
	 */
	public static final String TAG = "PostDatabase";

	/**
	 * Singleton instance
	 */
	private static PostDatabase database;


	/**
	 * database name
	 */
	public static String DATABASE_NAME = "Project.db";

	/**
	 * table name for BOOK_INFO
	 */
	public static String TABLE_POST = "post";

    /**
     * version
     */
	public static int DATABASE_VERSION = 1;


    /**
     * Helper class defined
     */
    private DatabaseHelper dbHelper;

    /**
     * Database object
     */
    private SQLiteDatabase db;


    private Context context;

    /**
     * Constructor
     */
	private PostDatabase(Context context) {
		this.context = context;
	}


	public static PostDatabase getInstance(Context context) {
		if (database == null) {
			database = new PostDatabase(context);
		}

		return database;
	}

	/**
	 * open database
	 *
	 * @return
	 */
    public boolean open() {
    	println("opening database [" + DATABASE_NAME + "].");

    	dbHelper = new DatabaseHelper(context);
    	db = dbHelper.getWritableDatabase();

    	return true;
    }

    /**
     * close database
     */
    public void close() {
    	println("closing database [" + DATABASE_NAME + "].");
    	db.close();
    	database = null;
    }

    /**
     * execute raw query using the input SQL
     * close the cursor after fetching any result
     *
     * @param SQL
     * @return
     */
    public Cursor rawQuery(String SQL) {
		println("\nexecuteQuery called.\n");

		Cursor c1 = null;
		try {
			c1 = db.rawQuery(SQL, null);
			println("cursor count : " + c1.getCount());
		} catch(Exception ex) {
    		Log.e(TAG, "Exception in executeQuery", ex);
    	}

		return c1;
	}

    public boolean execSQL(String SQL) {
		println("\nexecute called.\n");

		try {
			Log.d(TAG, "SQL : " + SQL);
			db.execSQL(SQL);
	    } catch(Exception ex) {
			Log.e(TAG, "Exception in executeQuery", ex);
			return false;
		}

		return true;
	}




    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase _db) {
        	// TABLE_BOOK_INFO
        	println("creating table [" + TABLE_POST + "].");

        	// drop existing table
        	String DROP_SQL = "drop table if exists " + TABLE_POST;
        	try {
        		_db.execSQL(DROP_SQL);
        	} catch(Exception ex) {
        		Log.e(TAG, "Exception in DROP_SQL", ex);
        	}

        	// create table
        	String CREATE_SQL = "create table " + TABLE_POST + "("
		        			+ "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
		        			+ "  TITLE TEXT, "
		        			+ "  CONTENTS TEXT, "
		        			+ "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
		        			+ ")";
            try {
            	_db.execSQL(CREATE_SQL);
            } catch(Exception ex) {
        		Log.e(TAG, "Exception in CREATE_SQL", ex);
        	}

			// insert 5 book records
			insertRecord(_db, "신촌 코리아아이", "안드로이드 기본과정 앱개발과정");
			insertRecord(_db, "애플의 개인정보 추적금지 ", "안드로이드 플랫폼을 갖고있는 구글이 예상을 웃도는 광고 매출을 올렸");
			insertRecord(_db, "누리호가 찍은 지구 ", "누리호가 찍은 지구");

		}

        public void onOpen(SQLiteDatabase db) {
        	println("opened database [" + DATABASE_NAME + "].");

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        	println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");

        	if (oldVersion < 2) {   // version 1

        	}

        }

		private void insertRecord(SQLiteDatabase _db, String title, String contents) {
			try {
				_db.execSQL( "insert into " + TABLE_POST + "(TITLE, CONTENTS) values ('" + title + "', '"  + contents + "');" );
			} catch(Exception ex) {
				Log.e(TAG, "Exception in executing insert SQL.", ex);
			}
		}

    }

	public void insertRecord(String title, String contents) {
		try {
			Log.e(TAG, "insert SQL.");
			db.execSQL( "insert into " + TABLE_POST + "(TITLE, CONTENTS) values ('" + title + "', '" + contents + "');" );
		} catch(Exception ex) {
			Log.e(TAG, "Exception in executing insert SQL.", ex);
		}
	}

	public ArrayList<PostInfo> selectAll() {
		ArrayList<PostInfo> result = new ArrayList<PostInfo>();

		try {
			Cursor cursor = db.rawQuery("select TITLE, CONTENTS from " + TABLE_POST, null);
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				String title = cursor.getString(0);
				String contents = cursor.getString(1);


				Log.e(TAG, "selectAll "+title);
				PostInfo info = new PostInfo(title, contents);
				result.add(info);
			}

		} catch(Exception ex) {
			Log.e(TAG, "Exception in executing insert SQL.", ex);
		}

		return result;
	}

    private void println(String msg) {
    	Log.d(TAG, msg);
    }


}
