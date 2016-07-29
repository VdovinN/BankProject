package com.example.vdovin.bankproject.orm.DAO;

import android.content.Context;

import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdovin on 6/19/16.
 */
public class BankExchangeRateDao extends AbstractDao<BankExchangeRate> {

    private BankExchangeRateDao instance;

    public BankExchangeRateDao(Context context) {
        super(context);
    }


    public List<BankExchangeRate> findByBankId(Long bankId) {
        List<BankExchangeRate> bankExchangeRateList = new ArrayList<>();
        open();
        try {
            Dao dao = dbHelper.getmBankExchangeRate();
            QueryBuilder<BankExchangeRate, Long> qb = dao.queryBuilder();
            bankExchangeRateList = qb.where().eq(BankExchangeRate.EXCHANGE_RATE_BANK_ID_FIELD, bankId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHelper.close();
        }

        return bankExchangeRateList;
    }

    public List<BankExchangeRate> findByCurrencyId(Long currencyId) {
        List<BankExchangeRate> bankExchangeRateList = new ArrayList<>();
        open();
        try {
            Dao dao = dbHelper.getmBankExchangeRate();
            QueryBuilder<BankExchangeRate, Long> qb = dao.queryBuilder();
            bankExchangeRateList = qb.where().eq(BankExchangeRate.EXCHANGE_RATE_CURRENCY_ID_FIELD, currencyId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHelper.close();
        }

        return bankExchangeRateList;
    }

    public Long nextCode(){
        Long result = null;
        open();
        try {
            Dao dao = dbHelper.getmBankExchangeRate();
            QueryBuilder<BankExchangeRate, Long> qb = dao.queryBuilder();
            if(dao.queryForAll().size() > 0) {
                result = qb.orderBy("id", false).queryForFirst().getId() + 1L;
            } else {
                result = 1L;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
