package com.olvera.cronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.olvera.cronoapp.model.Cronos
import kotlinx.coroutines.flow.Flow

@Dao
interface CronosDataBaseDao {

    @Query("SELECT * FROM cronos")
    fun getCronos(): Flow<List<Cronos>>

    @Query("SELECT * FROM cronos WHERE id = :id")
    fun getCronosById(id: Long): Flow<Cronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCronos(crono: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCronos(crono: Cronos)

    @Delete
    suspend fun deleteCronos(crono: Cronos)

}