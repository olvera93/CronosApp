package com.olvera.cronoapp.di

import android.content.Context
import androidx.room.Room
import com.olvera.cronoapp.room.CronosDataBase
import com.olvera.cronoapp.room.CronosDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCronosDao(cronoDataBase: CronosDataBase): CronosDataBaseDao =
        cronoDataBase.cronosDao()

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context): CronosDataBase {
        return Room.databaseBuilder(
            context,
            CronosDataBase::class.java,
            "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}