package kz.mukhamedissa.exchangerates.data.model;

/**
 * Created by Mukhamed Issa on 5/28/17.
 */

public class CurrencyCheck {

    private String mName;
    private boolean mChecked;

    public CurrencyCheck(String name, boolean checked) {
        this.mName = name;
        this.mChecked = checked;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        this.mChecked = checked;
    }

    @Override
    public String toString() {
        return mName;
    }
}
