package com.example.kotlin_tasktwo.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_tasktwo.Lesson_6.Service
import com.example.kotlin_tasktwo.Lesson_6.ThreadFragment
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.view.WeatherListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commitNow()
        }

startService(Intent(this,Service::class.java))
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