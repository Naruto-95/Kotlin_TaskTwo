package com.example.kotlin_tasktwo.Lesson_6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlin_tasktwo.utils.KAY_RECEIVER


class MyBroadcastReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val ex = it.getStringExtra(KAY_RECEIVER)
            Log.d("@@@"," MyBroadcastReceiver  onReceive $ex ")
        }
    }
}
