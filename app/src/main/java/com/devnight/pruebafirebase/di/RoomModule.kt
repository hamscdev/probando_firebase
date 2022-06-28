package com.devnight.pruebafirebase.di

import android.content.Context
import androidx.room.Room
import com.devnight.pruebafirebase.data.database.AppDataBase
import com.devnight.pruebafirebase.data.database.dao.RecomendationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    private const val DATABASE_NAME = "test_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext appContext: Context) = Room.databaseBuilder(appContext, AppDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDataBase) = db.recomendations()

}