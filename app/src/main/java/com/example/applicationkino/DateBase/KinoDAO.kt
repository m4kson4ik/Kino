package com.example.applicationkino.DateBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.applicationkino.TypeKinoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface KinoDAO {
    @Query("select * from typeKinoItem")
    fun getAllTypeKinoItem() : Flow<List<TypeKinoItem>>

    @Insert
    suspend fun insertTypeKinoItem(vararg items : TypeKinoItem)
}