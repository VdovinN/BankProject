package com.example.vdovin.bankproject.datasource;

import android.content.Context;

import com.example.vdovin.bankproject.Session;
import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.models.Currency;
import com.example.vdovin.bankproject.orm.DAO.BankDao;
import com.example.vdovin.bankproject.orm.DAO.BankExchangeRateDao;

import java.util.List;

/**
 * Created by vdovin on 7/17/16.
 */
public class BankExchangeRateDataSource {

    private Context context;
    private static BankExchangeRateDataSource mInstance;

    public BankExchangeRateDataSource(Context context) {
        this.context = context;
    }

    public static BankExchangeRateDataSource getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (BankExchangeRateDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BankExchangeRateDataSource(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public void insert(List<BankExchangeRate> bankExchangeRateList) {

        BankExchangeRateDao bankExchangeRateDao = new BankExchangeRateDao(context);
        List<Bank> allBanks = Session.loadAllBanks(context);
        List<Currency> allCurrencies = Session.loadAllCurrencies(context);

        bankExchangeRateDao.deleteAll(BankExchangeRate.class);

        int pos = 0;

        for (int i = 0; i < allBanks.size(); i++) {
            for (int j = 0; j < allCurrencies.size(); j++) {
                //int pos = ((i + 1) * (j + 1)) - 1;
                long id = bankExchangeRateDao.nextCode();
                bankExchangeRateList.get(pos).setId(id);
                bankExchangeRateList.get(pos).setBankId(allBanks.get(i));
                bankExchangeRateList.get(pos).setCurrencyId(allCurrencies.get(j));
                bankExchangeRateDao.insert(BankExchangeRate.class, bankExchangeRateList.get(pos));
                pos++;
            }
        }

    }


    public void clear(){
        BankExchangeRateDao bankExchangeRateDao = new BankExchangeRateDao(context);
        bankExchangeRateDao.deleteAll(BankExchangeRate.class);
    }

}
