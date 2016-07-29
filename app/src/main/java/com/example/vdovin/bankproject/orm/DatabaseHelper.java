package com.example.vdovin.bankproject.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.models.Bank;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.models.Currency;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by vdovin on 6/14/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "exchangerates.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Bank, Long> mBankDao;
    private Dao<Currency, Long> mCurrencyDao;
    private Dao<BankExchangeRate, Long> mBankExchangeRate;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Bank.class);
            TableUtils.createTable(connectionSource, Currency.class);
            TableUtils.createTable(connectionSource, BankExchangeRate.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Bank.class, true);
            TableUtils.dropTable(connectionSource, Currency.class, true);
            TableUtils.dropTable(connectionSource, BankExchangeRate.class, true);

            onCreate(database, connectionSource);
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Bank, Long> getmBankDao() throws SQLException{

        if(mBankDao == null){
            mBankDao = getDao(Bank.class);
        }
        return mBankDao;
    }

    public Dao<Currency, Long> getmCurrencyDao() throws SQLException{

        if(mCurrencyDao == null){
            mCurrencyDao = getDao(Currency.class);
        }
        return mCurrencyDao;
    }

    public Dao<BankExchangeRate, Long> getmBankExchangeRate() throws SQLException{

        if(mBankExchangeRate == null){
            mBankExchangeRate = getDao(BankExchangeRate.class);
        }
        return mBankExchangeRate;
    }

    @Override
    public void close() {
        mBankDao = null;
        mCurrencyDao = null;
        mBankExchangeRate = null;

        super.close();
    }
}
