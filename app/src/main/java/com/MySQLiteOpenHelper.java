package com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.anye.greendao.gen.AdminDao;
import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.ProductDao;
import com.anye.greendao.gen.SalerDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.table.Admin;
import com.table.Buyer;
import com.table.Order;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Koala Bee on 2018/11/8.
 */

public class MySQLiteOpenHelper extends DaoMaster.DevOpenHelper {
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },AdminDao.class,BuyerDao.class,SalerDao.class, ProductDao.class);
    }
}
