package com.example.kotlin_tasktwo.Lesson_6

import android.app.IntentService
import android.content.Intent
import android.util.Log

class Service(val name: String = ""): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","Работает")

    }



}