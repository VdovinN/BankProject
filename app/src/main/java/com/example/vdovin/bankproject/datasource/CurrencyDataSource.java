package com.example.vdovin.bankproject.datasource;

import android.content.Context;

import com.example.vdovin.bankproject.models.Currency;
import com.example.vdovin.bankproject.orm.DAO.CurrencyDao;

import java.util.List;

/**
 * Created by vdovin on 7/17/16.
 */
public class CurrencyDataSource {

    private Context context;
    private static CurrencyDataSource mInstance;

    public CurrencyDataSource(Context context) {
        this.context = context;
    }

    public static CurrencyDataSource getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (CurrencyDataSource.class) {
                if (mInstance == null) {
                    mInstance = new CurrencyDataSource(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public void insert(List<Currency> currencyList) {

        CurrencyDao currencyDao = new CurrencyDao(context);
        currencyDao.deleteAll(Currency.class);

        for (Currency currency : currencyList) {
            long id = currencyDao.nextCode();
            currency.setId(id);
            currencyDao.insert(Currency.class, currency);
        }

    }


    public void clear(){
        CurrencyDao currencyDao = new CurrencyDao(context);
        currencyDao.deleteAll(Currency.class);
    }

}
