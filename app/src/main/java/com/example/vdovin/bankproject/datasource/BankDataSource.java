package com.example.vdovin.bankproject.datasource;

import android.content.Context;

import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.orm.DAO.BankDao;

import java.util.List;

/**
 * Created by vdovin on 7/15/16.
 */
public class BankDataSource {

    private Context context;
    private static BankDataSource mInstance;

    public BankDataSource(Context context) {
        this.context = context;
    }

    public static BankDataSource getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (BankDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BankDataSource(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public void insert(List<Bank> bankList) {

        BankDao bankDao = new BankDao(context);
        bankDao.deleteAll(Bank.class);
        for (Bank bank : bankList) {
            long id = bankDao.nextCode();
            bank.setId(id);
            bankDao.insert(Bank.class, bank);
        }

    }

    public void clear(){
        BankDao bankDao = new BankDao(context);
        bankDao.deleteAll(Bank.class);
    }

}
