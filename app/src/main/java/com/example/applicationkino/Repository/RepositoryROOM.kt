package com.example.applicationkino.Repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.applicationkino.DI.IRepositoryROOM
import com.example.applicationkino.DateBase.KinoDateBase
import com.example.applicationkino.TypeKinoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryROOM @Inject constructor(db : KinoDateBase) : IRepositoryROOM{
    private val daoKino = db.getKinoDAO()

    fun getAllTypeKinoItem() : Flow<List<TypeKinoItem>> {
        Log.d("mgkit", "GETALLTYPEKINO" + daoKino.getAllTypeKinoItem())
        return daoKino.getAllTypeKinoItem()
    }

    suspend fun insertTypeKinoItem(items: List<TypeKinoItem>?)
    {
        Log.d("mgkit", "CreateTYPEKINOITEM" + items.toString())
        if (items != null)
        {
            items.map { it.dateCreate = LocalDateTime.now() }
            daoKino.insertTypeKinoItem(*items.toTypedArray())
        }
    }
    val scope = CoroutineScope(Dispatchers.Default)


    val isGetTypeKinoItem = MutableStateFlow(true)

    init {
        Log.d("mgkit", "INIZIALIZATION isGetTypeKinoItem")
        scope.launch {
            getAllTypeKinoItem().collect { it ->
                it.map {
                    val isCreate = it.dateCreate!! < LocalDateTime.now().plusDays(7)
                    isGetTypeKinoItem.emit(isCreate)
                    Log.d("mgkit", "ISDATE" + "${it.dateCreate} ------- ${LocalDateTime.now().plusDays(7)} ------- $isCreate")
                }
            }
        }
    }
}