package kz.mukhamedissa.exchangerates.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kz.mukhamedissa.exchangerates.util.db.orm.ExchangeRateORM;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */
public class DBWrapper extends SQLiteOpenHelper {

    private static final String TAG = DBWrapper.class.getCanonicalName();

    public static final String DATABASE_NAME = "exchangerate.db";
    public static final int DATABASE_VERSION = 1;

    public DBWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database [" + DATABASE_NAME + "]");

        db.execSQL(ExchangeRateORM.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database [" + DATABASE_NAME + "v." + oldVersion + "to v." + newVersion + "]");

        db.execSQL(ExchangeRateORM.SQL_DROP_TABLE);

        onCreate(db);
    }
}
