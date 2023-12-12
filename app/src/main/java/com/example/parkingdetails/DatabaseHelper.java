package com.example.parkingdetails;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE_NAME = "User";
    private static final String CARD_TABLE_NAME = "CardDetails";

    // User table columns
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PHONE = "phone";

    // CardDetails table columns
    private static final String CARD_COLUMN_ID = "id";
    private static final String CARD_COLUMN_EMAIL = "email"; // Foreign key
    private static final String CARD_COLUMN_BANK_NAME = "bank_name";
    private static final String CARD_COLUMN_CARD_NUMBER = "card_number";
    private static final String CARD_COLUMN_EXPIRE_DATE = "expire_date";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + " (" +
            USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_COLUMN_NAME + " TEXT, " +
            USER_COLUMN_PHONE + " TEXT);";

    private static final String CREATE_CARD_TABLE = "CREATE TABLE " + CARD_TABLE_NAME + " (" +
            CARD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CARD_COLUMN_EMAIL + " TEXT, " +
            CARD_COLUMN_BANK_NAME + " TEXT, " +
            CARD_COLUMN_CARD_NUMBER + " TEXT, " +
            CARD_COLUMN_EXPIRE_DATE + " TEXT, " +
            "FOREIGN KEY (" + CARD_COLUMN_EMAIL + ") REFERENCES " + USER_TABLE_NAME + "(" + USER_COLUMN_ID + "));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CARD_TABLE_NAME);
        onCreate(db);
    }

    // Additional database helper methods
    public void insrec(String name,String ph)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_COLUMN_NAME,name);
        values.put(USER_COLUMN_PHONE,ph);
        db.insert(USER_TABLE_NAME,null,values);
        db.close();
    }
    @SuppressLint("Range")
    public int getUserIdByPhoneNumber(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USER_COLUMN_ID};
        String selection = USER_COLUMN_PHONE + " = ?";
        String[] selectionArgs = {phoneNumber};
        Cursor cursor = db.query(USER_TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        int userId = -1; // Default value if not found
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex(USER_COLUMN_ID));
            cursor.close();
        }
        db.close();

        return userId;
    }
    @SuppressLint("Range")
    public List<CardItem> getCardDetailsByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<CardItem> cardItems = new ArrayList<>();
        String[] columns = {CARD_COLUMN_BANK_NAME, CARD_COLUMN_CARD_NUMBER, CARD_COLUMN_EXPIRE_DATE};
        String selection = CARD_COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query(CARD_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        Log.e("CardActivity", "No prob1----------------------------------------------------------------------------------------------------------------------------------.");

        String[] columns1 = {USER_COLUMN_NAME,USER_COLUMN_PHONE};
        String selection1 = USER_COLUMN_ID + " = ?";
        Cursor cursor1 = db.query(USER_TABLE_NAME, columns1, selection1, selectionArgs, null, null, null);
        Log.e("CardActivity", "No prob2----------------------------------------------------------------------------------------------------------------------------------.");

        String userName = null;
        String ph=null;// Default value if not found
        if (cursor1 != null && cursor1.moveToFirst()) {
            userName = cursor1.getString(cursor1.getColumnIndex(USER_COLUMN_NAME));
            ph=cursor1.getString(cursor1.getColumnIndex(USER_COLUMN_PHONE)); // Use cursor1 here
            cursor1.close();
        }

        Log.e("CardActivity", "No prob3----------------------------------------------------------------------------------------------------------------------------------.");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String bankName = cursor.getString(cursor.getColumnIndex(CARD_COLUMN_BANK_NAME));
                @SuppressLint("Range") String cardNumber = cursor.getString(cursor.getColumnIndex(CARD_COLUMN_CARD_NUMBER));
                @SuppressLint("Range") String expireDate = cursor.getString(cursor.getColumnIndex(CARD_COLUMN_EXPIRE_DATE));

                cardItems.add(new CardItem(userName, bankName, cardNumber, expireDate,ph));
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();

        return cardItems;
    }
    public void insCard(int userId, String bankName, String cardNumber, String expireDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_COLUMN_USER_ID, userId);
        values.put(CARD_COLUMN_BANK_NAME, bankName);
        values.put(CARD_COLUMN_CARD_NUMBER, cardNumber);
        values.put(CARD_COLUMN_EXPIRE_DATE, expireDate);
        db.insert(CARD_TABLE_NAME, null, values);
        db.close();
    }


}
