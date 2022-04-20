package com.example.kotlin_tasktwo.Lesson_6

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.kotlin_tasktwo.utils.*
import java.lang.Thread.sleep

class Service(val name: String = ""): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","Service")
        intent?.let {
            val ex = it.getStringExtra(KAY_SERVICE)
            Log.d("@@@","Service $ex ")
            sleep(SLEEP_SERVICE)
            val message = Intent(WAVE_MY_ACTION)
            message.putExtra(KAY_RECEIVER,"Hello,Activity")
            sendBroadcast(message)
        }
        }

    }



