package domain.room.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(enitiy: HistoryMyApplication)
    @Delete
    fun delete(enitiy: HistoryMyApplication)
    @Update
    fun update(enitiy: HistoryMyApplication)

    @Query("SELECT * FROM MyHistory WHERE city=:city")
   fun getHistoriForCity(city:String):List<HistoryMyApplication>
    @Query("SELECT * FROM MyHistory")
    fun getAll():List<HistoryMyApplication>



}