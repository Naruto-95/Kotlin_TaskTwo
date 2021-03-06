package com.example.kotlin_tasktwo.view.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.kotlin_tasktwo.MyApp
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.lesson_10.MapsFragment
import com.example.kotlin_tasktwo.lesson_6.MyBroadcastReceiver
import com.example.kotlin_tasktwo.lesson_6.Service
import com.example.kotlin_tasktwo.lesson_6.ThreadFragment
import com.example.kotlin_tasktwo.lesson_9.ContentProviderFragment
import com.example.kotlin_tasktwo.utils.KEY_SERVICE
import com.example.kotlin_tasktwo.utils.WAVE_MY_ACTION
import com.example.kotlin_tasktwo.view.WeatherListFragment
import domain.room.room.view_History.HistoryWeatherListFragment

//3:17

class MainActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commitNow()
        }

        startService(Intent(this, Service::class.java).apply {
            putExtra(KEY_SERVICE, "Hello,Service")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter(WAVE_MY_ACTION))//Глобальный
        // LocalBroadcastManager.getInstance(this )
        // .registerReceiver(receiver,IntentFilter("MyAction"))//Локальный
            /*val mySP = getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val editor = mySP.edit()
        editor.putBoolean(KEY_RUSSIAN, true)
        editor.apply()
        val defaultRussian = true
        mySP.getBoolean(KEY_RUSSIAN, defaultRussian)*/







    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuTheard -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ThreadFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }
            R.id.menuHistory -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HistoryWeatherListFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }
            R.id.menuContentProvider -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ContentProviderFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }
            R.id.menuGoogleMaps -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MapsFragment())
                    .addToBackStack("")
                    .commit()

            }

        }
        return super.onOptionsItemSelected(item)
    }


}