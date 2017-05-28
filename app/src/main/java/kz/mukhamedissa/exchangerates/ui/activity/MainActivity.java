package kz.mukhamedissa.exchangerates.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kz.mukhamedissa.exchangerates.AppSettings;
import kz.mukhamedissa.exchangerates.R;
import kz.mukhamedissa.exchangerates.data.loader.ExchangeRateLoader;
import kz.mukhamedissa.exchangerates.data.model.ExchangeRate;
import kz.mukhamedissa.exchangerates.data.response.ExchangeRateResponse;
import kz.mukhamedissa.exchangerates.service.UpdateExchangeRatesService;
import kz.mukhamedissa.exchangerates.ui.adapter.ExchangeRateListAdapter;
import kz.mukhamedissa.exchangerates.util.Utils;
import kz.mukhamedissa.exchangerates.util.db.orm.ORMFactory;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ExchangeRateResponse>{

    public static final String SERVER = "server";
    public static final String LOCAL_DB = "local_db";

    @StringDef({SERVER, LOCAL_DB})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Storage {}

    private RecyclerView mExchangeRateRecyclerView;
    private ExchangeRateListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadExchangeRates();
    }

    @Override
    public Loader<ExchangeRateResponse> onCreateLoader(int id, Bundle args) {
        ArrayList<String> rateIds = Utils.generateRateIds(AppSettings.getString(AppSettings.BASE_CURRENCY, AppSettings.DEFAULT_CURRENCY),
                AppSettings.getString(AppSettings.SELECTED_CURRENCIES, AppSettings.DEFAULT_SELECTED_CURRENCIES));

        return new ExchangeRateLoader(this,
                ExchangeRateLoader.createBundle(AppSettings.getString(AppSettings.BASE_CURRENCY,
                        AppSettings.DEFAULT_CURRENCY), rateIds));
    }

    @Override
    public void onLoadFinished(Loader<ExchangeRateResponse> loader, ExchangeRateResponse data) {
        if (data.isSuccess() && data.result != null) {
            ORMFactory.getArticleORM().insert(this, data.result);
            mAdapter.setData(data.result);
            startUpdateService();
        }
    }

    @Override
    public void onLoaderReset(Loader<ExchangeRateResponse> loader) {

    }

    private void initUI() {
        mExchangeRateRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);
        mExchangeRateRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ExchangeRateListAdapter();
        mExchangeRateRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                SettingsActivity.start(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadExchangeRates() {
        if (Utils.isInternetAvailable(this)) {
            ORMFactory.getArticleORM().delete(this);
            getSupportLoaderManager().restartLoader(0, null, this);
        } else {
            List<ExchangeRate> exchangeRates = ORMFactory.getArticleORM().get(this);
            mAdapter.setData(exchangeRates);

            Snackbar.make(mExchangeRateRecyclerView, getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
        }
    }

    private void startUpdateService() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);

        Intent intent = new Intent(this, UpdateExchangeRatesService.class);

        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                43200000, pintent);

        startService(new Intent(getBaseContext(), UpdateExchangeRatesService.class));
    }
}
