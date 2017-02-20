package com.example.uditsetia.smsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by uditsetia on 20/02/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
  private final static String DB_NAME = "com.example.uditsetia.smsapp";
  private static int DB_VERSION = 1;
  private static MyDatabaseHelper databaseHelper;


  public MyDatabaseHelper (Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate (SQLiteDatabase db) {
    db.execSQL(TableSentMessage.CREATE_TABLE);
  }

  @Override
  public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public static MyDatabaseHelper getDatabaseHelper (Context context) {
    if (databaseHelper == null) {
      databaseHelper = new MyDatabaseHelper(context);
    }
    return databaseHelper;
  }
}
