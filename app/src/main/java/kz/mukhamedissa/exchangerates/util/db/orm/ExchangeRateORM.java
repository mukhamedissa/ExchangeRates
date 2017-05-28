package kz.mukhamedissa.exchangerates.util.db.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;
import kz.mukhamedissa.exchangerates.util.db.DBWrapper;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRateORM implements IOrm {

    private static final String TAG = ExchangeRateORM.class.getCanonicalName();

    private static final String TABLE_NAME = "exchange_rate";
    private static final String COMMA_SEPARATOR = ", ";

    private static final String COLUMN_ID_TYPE = "TEXT";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_NAME_TYPE = "TEXT";
    private static final String COLUMN_NAME = "name";

    private static final String COLUMN_RATE_TYPE = "TEXT";
    private static final String COLUMN_RATE = "rate";

    private static final String COLUMN_DATE_TYPE = "TEXT";
    private static final String COLUMN_DATE = "date";

    private static final String COLUMN_TIME_TYPE = "TEXT";
    private static final String COLUMN_TIME = "time";

    private static final String COLUMN_ASK_TYPE = "TEXT";
    private static final String COLUMN_ASK = "ask";

    private static final String COLUMN_BID_TYPE = "TEXT";
    private static final String COLUMN_BID = "bid";

    private static final String UNIQUE_CONSTRAINT = "UNIQUE";
    private static final String ON_CONFLICT_CLAUSE = "ON CONFLICT REPLACE";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " " + COLUMN_ID_TYPE + " " + UNIQUE_CONSTRAINT + " " + ON_CONFLICT_CLAUSE + COMMA_SEPARATOR +
            COLUMN_NAME + " " + COLUMN_NAME_TYPE + COMMA_SEPARATOR +
            COLUMN_RATE + " " + COLUMN_RATE_TYPE + COMMA_SEPARATOR +
            COLUMN_DATE + " " + COLUMN_DATE_TYPE + COMMA_SEPARATOR +
            COLUMN_TIME  + " " + COLUMN_TIME_TYPE + COMMA_SEPARATOR +
            COLUMN_ASK + " " + COLUMN_ASK_TYPE + COMMA_SEPARATOR +
            COLUMN_BID + " " + COLUMN_BID_TYPE +
            ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    @Override
    public void insert(Context context, List<ExchangeRate> item) {
        DBWrapper databaseWrapper = new DBWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        for (ExchangeRate exchangeRate : item) {
            ContentValues contentValues = objectToContentValues(exchangeRate);
            database.insert(TABLE_NAME, "null", contentValues);

            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            Log.i(TAG, "Inserted articles with title: " + exchangeRate.getName());
            Log.i(TAG, "There are " + cursor.getCount() + " articles");
        }

        database.close();
    }

    @Override
    public List<ExchangeRate> get(Context context) {
        DBWrapper databaseWrapper = new DBWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        List<ExchangeRate> exchangeRates = new ArrayList<>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                ExchangeRate exchangeRate = cursorToObject(cursor);
                exchangeRates.add(exchangeRate);
                cursor.moveToNext();
            }
            Log.i(TAG, "Articles are loaded");
        }
        database.close();

        return exchangeRates;
    }

    @Override
    public ContentValues objectToContentValues(ExchangeRate item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, item.getName());
        contentValues.put(COLUMN_RATE, item.getRate());
        contentValues.put(COLUMN_DATE, item.getDate());
        contentValues.put(COLUMN_TIME, item.getTime());
        contentValues.put(COLUMN_ASK, item.getAsk());
        contentValues.put(COLUMN_BID, item.getBid());

        return contentValues;
    }

    @Override
    public ExchangeRate cursorToObject(Cursor cursor) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        exchangeRate.setRate(cursor.getString(cursor.getColumnIndex(COLUMN_RATE)));
        exchangeRate.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
        exchangeRate.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
        exchangeRate.setAsk(cursor.getString(cursor.getColumnIndex(COLUMN_ASK)));
        exchangeRate.setBid(cursor.getString(cursor.getColumnIndex(COLUMN_BID)));

        return exchangeRate;
    }

    @Override
    public void delete(Context context) {
        DBWrapper wrapper = new DBWrapper(context);
        SQLiteDatabase database = wrapper.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME);

        database.close();
    }
}
