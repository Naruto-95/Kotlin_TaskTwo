package com.example.kotlin_tasktwo.lesson11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.lesson_10.MapsFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyService: FirebaseMessagingService() {
    /*
    Server_key:
    AAAASS8votA:APA91bEXelpYI_9aDxX7c9tDbDM6dzRxXu5d0Pkeh4BKNfASnrhYJy_TY3f8_zhJ4ntgSAEYG0tFxr_
    8jDI0M5FBH7ZX9S-Hxc4Df_W27x2w-vw10eqBi1XkHk_r0ckK4UtjPdSqOudb
     */

    override fun onMessageReceived(message: RemoteMessage) {
        if (!message.data.isNullOrEmpty()){
            val title = message.data[KEY_TITLE]
            val message = message.data[KEY_MESSAGE]
            if (!title.isNullOrEmpty() && !message.isNullOrEmpty()){
                push(title,message)
            }

        }

    }

    companion object {
        private const val NOTIFICATION_ID_HIGH = 1
        private const val NOTIFICATION_ID_LOW = 2
        private const val CHANNEL_ID_HIGH = "channel_id_1"
        private const val CHANNEL_ID_LOW = "channel_id_2"

        private const val KEY_TITLE = "myTitle"
        private const val KEY_MESSAGE = "myMassage"
    }


    private fun push(title:String,massage:String){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(applicationContext,MapsFragment::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilderHIGH = NotificationCompat.Builder(this, CHANNEL_ID_HIGH).apply {
            setSmallIcon(R.drawable.ic_cahnnalone)
            setContentTitle(title)
            setContentText(massage)
            setContentIntent(contentIntent)
            priority = NotificationManager.IMPORTANCE_HIGH
        }

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channelNameHIGH= "Name $CHANNEL_ID_HIGH "
            val channelDescriptionHIGH = "Description $CHANNEL_ID_HIGH "
            val channelPriorityHIGH = NotificationManager.IMPORTANCE_HIGH
            val channelHIGH = NotificationChannel(CHANNEL_ID_HIGH,channelNameHIGH,channelPriorityHIGH).apply {
                description = channelDescriptionHIGH
            }
            notificationManager.createNotificationChannel(channelHIGH)
        }
        notificationManager.notify(NOTIFICATION_ID_HIGH,notificationBuilderHIGH.build())


    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}