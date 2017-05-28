package kz.mukhamedissa.exchangerates.util.db.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public interface IOrm {

    void insert(Context context, List<ExchangeRate> item);
    List<ExchangeRate> get(Context context);
    ContentValues objectToContentValues(ExchangeRate item);
    ExchangeRate cursorToObject(Cursor cursor);
    void delete(Context context);
}
