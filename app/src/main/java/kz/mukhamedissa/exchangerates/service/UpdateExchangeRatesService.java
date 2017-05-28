package kz.mukhamedissa.exchangerates.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import kz.mukhamedissa.exchangerates.AppSettings;
import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;
import kz.mukhamedissa.exchangerates.data.response.ExchangeRateResponse;
import kz.mukhamedissa.exchangerates.network.YahooCurrencyAPIClient;
import kz.mukhamedissa.exchangerates.util.Utils;
import kz.mukhamedissa.exchangerates.util.db.orm.ORMFactory;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class UpdateExchangeRatesService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new ExchangeLoaderTask().execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class ExchangeLoaderTask extends AsyncTask<Void, Void, ExchangeRateResponse> {

        @Override
        protected ExchangeRateResponse doInBackground(Void... voids) {
            try {
                ArrayList<String> rateIds = Utils.generateRateIds(AppSettings.getString(AppSettings.BASE_CURRENCY, AppSettings.DEFAULT_CURRENCY),
                        AppSettings.getString(AppSettings.SELECTED_CURRENCIES, AppSettings.DEFAULT_SELECTED_CURRENCIES));
                List<ExchangeRate> exchangeRates = YahooCurrencyAPIClient.getExchangeRates(rateIds);

                return new ExchangeRateResponse(exchangeRates);
            } catch (UnsupportedEncodingException e) {
                return new ExchangeRateResponse(e);
            }
        }

        @Override
        protected void onPostExecute(ExchangeRateResponse response) {
            super.onPostExecute(response);

            if (response.isSuccess() && response.result != null) {
                ORMFactory.getArticleORM().delete(getBaseContext());
                ORMFactory.getArticleORM().insert(getBaseContext(), response.result);
            }
        }
    }
}
