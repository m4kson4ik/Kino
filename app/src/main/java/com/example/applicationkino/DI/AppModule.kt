package com.example.applicationkino.DI

import android.app.Application
import androidx.room.Room
import com.example.applicationkino.DateBase.KinoDateBase
import com.example.applicationkino.IRepositoryAPI
import com.example.applicationkino.Repository.APIRepository
import com.example.applicationkino.Repository.RepositoryROOM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDateBase(app : Application) : KinoDateBase
    {
        return Room.databaseBuilder(app, KinoDateBase::class.java, "kino_db").build()
    }

    @Provides
    fun provideRepositoryAPI() : IRepositoryAPI
    {
        return APIRepository()
    }

    @Provides
    fun provideRepositoryROOM(dateBase: KinoDateBase) : IRepositoryROOM
    {
        return RepositoryROOM(dateBase)
    }
}