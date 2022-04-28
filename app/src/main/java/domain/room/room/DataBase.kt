package domain.room.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryMyApplication::class), version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun historyDao():HistoryDao

}