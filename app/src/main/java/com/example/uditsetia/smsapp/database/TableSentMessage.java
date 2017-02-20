package com.example.uditsetia.smsapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uditsetia.smsapp.model.SentMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uditsetia on 20/02/17.
 */

public class TableSentMessage {
  public static final String TABLE_NAME = "sent_message";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_TIME = "time";
  public static final String COLUMN_OTP = "otp";

  public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
          COLUMN_NAME + " TEXT," +
          COLUMN_TIME + " TEXT," +
          COLUMN_OTP + " Text" +
          ");";

  public static List<SentMessage> queryForSentMessages (SQLiteDatabase database) {
    Cursor cursor = database.query(TABLE_NAME, new String[]{COLUMN_NAME, COLUMN_TIME, COLUMN_OTP}, null, null, null, null, COLUMN_TIME + " DESC");
    List<SentMessage> sentMessageList = null;

    if (cursor != null && cursor.moveToFirst()) {
      sentMessageList = new ArrayList<>();
      do {
        String name = cursor.getString(0);
        long time = cursor.getLong(1);
        int otp = cursor.getInt(2);
        SentMessage sentMessage = new SentMessage(name, time, otp);
        sentMessageList.add(sentMessage);
      } while (cursor.moveToNext());
      cursor.close();
    }

    return sentMessageList;
  }

  public static long insertIntoTable (SQLiteDatabase database, String name, long time, int otp) {
    ContentValues values = new ContentValues();
    values.put(COLUMN_NAME, name);
    values.put(COLUMN_TIME, time);
    values.put(COLUMN_OTP, otp);

    return database.insert(TABLE_NAME, null, values);
  }

}
