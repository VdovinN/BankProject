package com.example.vdovin.bankproject.orm.DAO;

import android.content.Context;
import android.util.Log;

import com.example.vdovin.bankproject.orm.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by vdovin on 6/19/16.
 */
public abstract class AbstractDao<T> {

    protected DatabaseHelper dbHelper;
    private Context context;

    public AbstractDao(Context context){

        this.context = context;

    }

    public void open(){

        if(dbHelper == null || !dbHelper.isOpen()){
            dbHelper = new DatabaseHelper(context);
        }
    }

    public void insert(Class<T> clazz, T t){

        try{

            open();
            dbHelper.getDao(clazz).create(t);

        }catch (SQLException e){
            Log.e("Database", "Error in AbstractDao - INSERT");
        }finally {
            dbHelper.close();
        }

    }

    public void insertAll(Class<T> clazz, List<T> tList){

        try{
                open();
            for(T t2 : tList){
                dbHelper.getDao(clazz).create(t2);
            }
        }catch (SQLException e){
            Log.e("Database", "Error in AbstractDao - INSERT ALL");
        }finally {
            dbHelper.close();
        }

    }

    public void update(Class<T> clazz, T t) {
        try {
            open();
            dbHelper.getDao(clazz).createOrUpdate(t);
        } catch (SQLException e) {
            Log.e("Database", "Error in AbstractDao - UPDATE");
        }finally {
            dbHelper.close();
        }
    }

    public List<T> findAll(Class<T> clazz) {
        List<T> list = null;
        try {
            open();
            list = dbHelper.getDao(clazz).queryForAll();
        } catch (SQLException e) {
            Log.e("Database", "Error in AbstractDao - FIND ALL");
        }finally {
            dbHelper.close();
        }
        return list;
    }

    public void delete(Class<T> clazz, T t) {
        try {
            open();
            dbHelper.getDao(clazz).delete(t);
        } catch (SQLException e) {
            Log.e("Database", "Error in AbstractDao - DELETE");
        }finally {
            dbHelper.close();
        }
    }

    public int deleteAll(Class<T> clazz){

        try{
            open();
            return dbHelper.getDao(clazz).deleteBuilder().delete();
        } catch (SQLException e){
            Log.e("Database", "Error in AbstractDao - DELETE ALL");

        }finally {
            dbHelper.close();
        }
        return 0;
    }

    public int deleteAll2(Class<T> clazz){
        try {
          open();
            return TableUtils.clearTable(dbHelper.getConnectionSource(), clazz);
        }catch (SQLException e){
            Log.e("Database", "Error in AbstractDao - DELETE ALL2");

        }finally {
            dbHelper.close();
        }
        return 0;

    }

    public T findById(Class<T> clazz, Long id) {
        T t = null;
        try {
            open();
            Dao<T, Long> dao = dbHelper.getDao(clazz);
            t = dao.queryForId(id);
        } catch (SQLException e) {
            Log.e("Database", "Error in AbstractDao - FIND BY ID");
        }
        return t;
    }

}
