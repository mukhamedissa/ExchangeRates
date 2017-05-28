package kz.mukhamedissa.exchangerates;

import android.app.Application;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ExchangeRatesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!AppSettings.isInstantiated()) {
            AppSettings.init(this);
        }
    }
}
