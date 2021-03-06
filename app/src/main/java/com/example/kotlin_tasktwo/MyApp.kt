package com.example.kotlin_tasktwo

import android.app.Application
import androidx.room.Room
import domain.room.room.DataBase
import domain.room.room.HistoryDao


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        var db: DataBase? = null
        var appContext: MyApp? = null

        fun getHistoryDao(): HistoryDao {

               if (null == db) {
                   if (null != appContext) {
                       db = Room.databaseBuilder(appContext!!, DataBase::class.java, " 111 ").build()
                   } else {
                       throw IllegalStateException(" something went wrong with AppContext ")
                   }

               }



            return db!!.historyDao()
        }


    }


}








