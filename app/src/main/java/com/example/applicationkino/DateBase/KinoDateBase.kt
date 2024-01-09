package com.example.applicationkino.DateBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applicationkino.LocalDateTimeConverter
import com.example.applicationkino.TypeKinoItem

@Database(entities = [TypeKinoItem::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class KinoDateBase : RoomDatabase() {
    abstract fun getKinoDAO() : KinoDAO
}