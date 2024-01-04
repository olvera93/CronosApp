package com.olvera.cronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olvera.cronoapp.model.Cronos

@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDataBase: RoomDatabase() {

    abstract fun cronosDao() : CronosDataBaseDao

}