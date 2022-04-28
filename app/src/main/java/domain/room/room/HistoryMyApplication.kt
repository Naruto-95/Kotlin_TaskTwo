package domain.room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyHistory")
data class HistoryMyApplication(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val city: String,
    val temperature: Int ,
    val feelsLike: Int ,
    val icon: String
)
