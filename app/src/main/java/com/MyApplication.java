package com;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;


/**
 * Created by Koala Bee on 2018/11/7.
 */

public class MyApplication extends Application {
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        setDatabase();

    }

    public static MyApplication getInstances() {
        return instance;
    }

    private void setDatabase() {
        helper = new MySQLiteOpenHelper(this, "Storedb");
        db=helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }


}
