package kz.mukhamedissa.exchangerates.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Set;

import kz.mukhamedissa.exchangerates.AppSettings;
import kz.mukhamedissa.exchangerates.R;
import kz.mukhamedissa.exchangerates.data.model.CurrencyCheck;
import kz.mukhamedissa.exchangerates.ui.adapter.CurrencyListAdapter;
import kz.mukhamedissa.exchangerates.util.Currency;

public class SettingsActivity extends AppCompatActivity {

    private TextView mBaseCurrencyView;

    private CurrencyListAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
    }

    private void initUI() {
        mBaseCurrencyView = (TextView) findViewById(R.id.activity_settings_base_currency_text);

        mBaseCurrencyView.setText(getString(R.string.base_currency_string,
                AppSettings.getString(AppSettings.BASE_CURRENCY, AppSettings.DEFAULT_CURRENCY)));

        findViewById(R.id.activity_settings_base_currency_row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrencyPickerDialog();
            }
        });

        findViewById(R.id.activity_settings_currencies_row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrenciesDialog();
            }
        });
    }

    private void showCurrencyPickerDialog() {
        List<Currency> currencies = Currency.getCurrenciesList();

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_base_currency_dialog);

        ListView listView = (ListView) dialog.findViewById(R.id.layout_base_currency_dialog_list);
        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, currencies);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String baseCurrency = adapterView.getItemAtPosition(i).toString();
                AppSettings.putString(AppSettings.BASE_CURRENCY, baseCurrency);

                mBaseCurrencyView.setText(getString(R.string.base_currency_string,
                        AppSettings.getString(AppSettings.BASE_CURRENCY, AppSettings.DEFAULT_CURRENCY)));

                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void showCurrenciesDialog() {
        Gson gson = new Gson();
        List<CurrencyCheck> currencies = gson.fromJson(AppSettings
                .getString(AppSettings.SELECTED_CURRENCIES, AppSettings.DEFAULT_SELECTED_CURRENCIES), new TypeToken<List<CurrencyCheck>>(){}.getType());


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_base_currency_dialog);

        ListView listView = (ListView) dialog.findViewById(R.id.layout_base_currency_dialog_list);
        mAdapter = new CurrencyListAdapter(this,
                R.layout.layout_currencies_list_item, currencies);

        listView.setAdapter(mAdapter);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                List<CurrencyCheck> currencyCheckList = mAdapter.getCurrencyList();
                int checkedCount = 0;
                for (CurrencyCheck currencyCheck: currencyCheckList) {
                    if (currencyCheck.isChecked()) {
                        checkedCount++;
                    }
                }

                if (checkedCount == 1) {
                    Toast.makeText(SettingsActivity.this,
                            getString(R.string.check_more_than_one_error), Toast.LENGTH_LONG).show();

                    showCurrenciesDialog();
                } else {
                    String selectedCurrencies = new Gson().toJson(currencyCheckList);
                    AppSettings.putString(AppSettings.SELECTED_CURRENCIES, selectedCurrencies);
                }

            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
