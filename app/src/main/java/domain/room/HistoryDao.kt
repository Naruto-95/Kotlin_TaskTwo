package domain.room

import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(enitiy:HistoryMyApplication)
    @Delete
    fun delete(enitiy:HistoryMyApplication)
    @Update
    fun update(enitiy:HistoryMyApplication)
    @Query("SELECT * FROM HistoryMyApplication")
    fun getAll(enitiy: HistoryMyApplication)
    @Query("SELECT * FROM HistoryMyApplication WHERE city = city")
    fun getHistoriForCity(city:String):List<HistoryMyApplication>
}