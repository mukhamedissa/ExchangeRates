package kz.mukhamedissa.exchangerates.util.db.orm;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class ORMFactory {

    private ORMFactory() {
        throw new IllegalStateException(ORMFactory.class.getCanonicalName() + " cannot be instantiated");
    }

    private static ExchangeRateORM sExchangeRateORM = null;

    public static ExchangeRateORM getArticleORM() {
        if(sExchangeRateORM == null) {
            sExchangeRateORM = new ExchangeRateORM();
        }

        return sExchangeRateORM;
    }

}
