package com.example.vdovin.bankproject;

import android.content.Context;

import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.models.Currency;
import com.example.vdovin.bankproject.orm.DAO.BankDao;
import com.example.vdovin.bankproject.orm.DAO.BankExchangeRateDao;
import com.example.vdovin.bankproject.orm.DAO.CurrencyDao;

import java.util.List;

/**
 * Created by vdovin on 7/20/16.
 */
public class Session {

    public static List<Bank> loadAllBanks(Context context){
        List<Bank> bankList;
        BankDao bankDao = new BankDao(context);
        bankList = bankDao.findAll(Bank.class);
        return bankList;
    }

    public static List<Currency> loadAllCurrencies(Context context){
        List<Currency> currencyList;
        CurrencyDao currencyDao = new CurrencyDao(context);
        currencyList = currencyDao.findAll(Currency.class);
        return currencyList;
    }

    public static List<BankExchangeRate> loadAllBankExchangeRates(Context context){
        List<BankExchangeRate> bankExchangeRateList;
        BankExchangeRateDao bankExchangeRateDao = new BankExchangeRateDao(context);
        bankExchangeRateList = bankExchangeRateDao.findAll(BankExchangeRate.class);
        return bankExchangeRateList;
    }


    public static List<BankExchangeRate> loadRatesByBankId(Context context, Long bankId){
        BankExchangeRateDao bankExchangeRateDao = new BankExchangeRateDao(context);
        return bankExchangeRateDao.findByBankId(bankId);
    }

    public static List<BankExchangeRate> loadRatesByCurrencyId(Context context, Long id){
        BankExchangeRateDao bankExchangeRateDao = new BankExchangeRateDao(context);
        return bankExchangeRateDao.findByCurrencyId(id);
    }




}
