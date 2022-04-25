package com.example.kotlin_tasktwo.view.main

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_tasktwo.Lesson_6.MyBroadcastReceiver
import com.example.kotlin_tasktwo.Lesson_6.Service
import com.example.kotlin_tasktwo.Lesson_6.ThreadFragment
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.utils.KEY_SERVICE
import com.example.kotlin_tasktwo.utils.WAVE_MY_ACTION
import com.example.kotlin_tasktwo.view.WeatherListFragment
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

startService(Intent(this,Service::class.java).apply {
    putExtra(KEY_SERVICE,"Hello,Service")
})

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter(WAVE_MY_ACTION))//Глобальный
       // LocalBroadcastManager.getInstance(this )
    // .registerReceiver(receiver,IntentFilter("MyAction"))//Локальный

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_thread,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuTheard ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ThreadFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }

        }
        return super.onOptionsItemSelected(item)
    }


}