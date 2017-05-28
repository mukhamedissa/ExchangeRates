package kz.mukhamedissa.exchangerates.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.mukhamedissa.exchangerates.data.model.CurrencyCheck;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */
public enum Currency {

    AFN("AFN"), ALL("ALL"), AMD("AMD"), AED("AED"), ANG("ANG"), AOA("AOA"), ARS("ARS"), AUD("AUD"),
    AWG("AWG"), AZN("AZN"), BAM("BAM"), BBD("BBD"), BDT("BDT"), BGN("BGN"), BHD("BHD"), BIF("BIF"),
    BMD("BMD"), BND("BND"), BOB("BOB"), BOV("BOV"), BRL("BRL"), BSD("BSD"), BTN("BTN"), BWP("BWP"),
    BYR("BYR"), BZD("BZD"), CAD("CAD"), CDF("CDF"), CHE("CHE"), CHF("CHF"), CHW("CHW"), CLF("CLF"),
    CLP("CLP"), CNY("CNY"), COP("COP"), COU("COU"), CRC("CRC"), CUC("CUC"), CUP("CUP"), CVE("CVE"),
    CZK("CZK"), DJF("DJF"), DKK("DKK"), DOP("DOP"), DZD("DZD"), EGP("EGP"), ERN("ERN"), ETB("ETB"),
    EUR("EUR"), FJD("FJD"), FKP("FKP"), GBP("GBP"), GEL("GEL"), GHS("GHS"), GIP("GIP"), GMD("GMD"),
    GNF("GNF"), GTQ("GTQ"), GYD("GYD"), HKD("HKD"), HNL("HNL"), HRK("HRK"), HTG("HTG"), HUF("HUF"),
    IDR("IDR"), ILS("ILS"), INR("INR"), IQD("IQD"), IRR("IRR"), ISK("ISK"), JMD("JMD"), JOD("JOD"),
    JPY("JPY"), KES("KES"), KGS("KGS"), KHR("KHR"), KMF("KMF"), KPW("KPW"), KRW("KRW"), KWD("KWD"),
    KYD("KYD"), KZT("KZT"), LAK("LAK"), LBP("LBP"), LKR("LKR"), LRD("LRD"), LSL("LSL"), LTL("LTL"),
    LYD("LYD"), MAD("MAD"), MDL("MDL"), MGA("MGA"), MKD("MKD"), MMK("MMK"), MNT("MNT"), MOP("MOP"),
    MRO("MRO"), MUR("MUR"), MVR("MVR"), MWK("MWK"), MXN("MXN"), MXV("MXV"), MYR("MYR"), MZN("MZN"),
    NAD("NAD"), NGN("NGN"), NIO("NIO"), NOK("NOK"), NPR("NPR"), NZD("NZD"), OMR("OMR"), PAB("PAB"),
    PEN("PEN"), PGK("PGK"), PHP("PHP"), PKR("PKR"), PLN("PLN"), PYG("PYG"), QAR("QAR"), RON("RON"),
    RSD("RSD"), RUB("RUB"), RWF("RWF"), SAR("SAR"), SBD("SBD"), SCR("SCR"), SDG("SDG"), SEK("SEK"),
    SGD("SGD"), SHP("SHP"), SLL("SLL"), SOS("SOS"), SRD("SRD"), SSP("SSP"), STD("STD"), SYP("SYP"),
    SZL("SZL"), THB("THB"), TJS("TJS"), TMT("TMT"), TND("TND"), TOP("TOP"), TRY("TRY"), TTD("TTD"),
    TWD("TWD"), TZS("TZS"), UAH("UAH"), UGX("UGX"), USD("USD"), USN("USN"), USS("USS"), UYI("UYI"),
    UYU("UYU"), UZS("UZS"), VEF("VEF"), VND("VND"), VUV("VUV"), WST("WST"), XAF("XAF"), XAG("XAG"),
    XAU("XAU"), XBA("XBA"), XBB("XBB"), XBC("XBC"), XBD("XBD"), XBT("XBT"), XCD("XCD"), XDR("XDR"),
    XFU("XFU"), XOF("XOF"), XPD("XPD"), XPF("XPF"), XPT("XPT"), XSU("XSU"), XTS("XTS"), XUA("XUA"),
    XXX("XXX"), YER("YER"), ZAR("ZAR"), ZMW("ZMW"), ZWD("ZWD");

    private final String symbol;

    private static final Map<String, Currency> lookup = new HashMap<>();
    private static final List<Currency> currencies = new ArrayList<>();
    private static final List<CurrencyCheck> currencyCheck = new ArrayList<>();

    static {
        for (Currency c : Currency.values()) {
            lookup.put(c.toString(), c);
        }

        for (Currency c : Currency.values()) {
            currencies.add(c);
        }

        for (Currency c : Currency.values()) {
            currencyCheck.add(new CurrencyCheck(c.toString(), false));
        }
    }

    Currency(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Currency get(String symbol) {
        return lookup.get(symbol);
    }

    public static List<Currency> getCurrenciesList() {
        return currencies;
    }

    public static List<CurrencyCheck> getCurrencyCheckList() {
        return currencyCheck;
    }
}
