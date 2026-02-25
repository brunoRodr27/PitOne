package com.example.pitone.di

import android.content.Context
import androidx.room.Room
import com.example.pitone.data.local.AppDatabase
import com.example.pitone.data.local.dao.FavoriteDriverDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pitone_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDriverDao(database: AppDatabase): FavoriteDriverDao{
        return database.favoriteDriverDao()
    }

}