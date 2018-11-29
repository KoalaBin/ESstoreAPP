package com.koalabee.esstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


import com.anye.greendao.gen.AdminDao;
import com.anye.greendao.gen.DaoSession;
import com.example.koalabee.esstoreapp.R;
import com.table.Admin;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Boolean isExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);

        preferences= getSharedPreferences("setting",MODE_PRIVATE);
        editor=preferences.edit();
        isExist = preferences.getBoolean("isExist", false);
        if (!isExist) {
           Admin admin=new Admin();
           admin.setName("admin");
           admin.setPassword("admin");

           DaoSession daoSession=MyApplication.getInstances().getDaoSession();
           AdminDao adminDao=daoSession.getAdminDao();
           adminDao.insert(admin);

           editor.putBoolean("isExist",true);
           editor.commit();
        }
    }

}

