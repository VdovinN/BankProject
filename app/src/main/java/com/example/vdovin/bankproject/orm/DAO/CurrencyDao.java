package com.example.vdovin.bankproject.orm.DAO;

import android.content.Context;

import com.example.vdovin.bankproject.models.Currency;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

/**
 * Created by vdovin on 6/19/16.
 */
public class CurrencyDao extends AbstractDao<Currency> {
    public CurrencyDao(Context context) {
        super(context);
    }

    public Long nextCode(){
        Long result = null;
        open();
        try {
            Dao dao = dbHelper.getmCurrencyDao();
            QueryBuilder<Currency, Long> qb = dao.queryBuilder();
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
