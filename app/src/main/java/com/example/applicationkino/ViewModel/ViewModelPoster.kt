package com.example.applicationkino.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationkino.ErrorList
import com.example.applicationkino.ModelSearch.Doc
import com.example.applicationkino.ModelSearch.ModelSearch
import com.example.applicationkino.Person.Person
import com.example.applicationkino.Repository.APIRepository
import com.example.applicationkino.Repository.RepositoryROOM
import com.example.applicationkino.TypeKinoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ViewModelPoster @Inject constructor(val repositoryAPI : APIRepository, val repositoryROOM: RepositoryROOM) : ViewModel() {
    var stateFlowPoster = repositoryAPI.getMovieSearch("Россия", null, null, null, null)

    var stateFlowPosterPopular = repositoryAPI.getMovieSearch("Россия", "8-10", "votes.russianFilmCritics", "-1", null)

    val stateFlowTypeKino = MutableStateFlow<List<TypeKinoItem>?>(null)

    val stateFlowTickets = repositoryAPI.getMovieSearch("Россия", null, null, null, "true")

    var errorEx = MutableStateFlow(ErrorList.WAIT)

    var scope = CoroutineScope(Dispatchers.Default)

    val stateFlowPeronItem = MutableStateFlow<Person?>(null)

    var selectedKino = MutableStateFlow<Doc?>(null)

    init {
        viewModelScope.launch {
            //  Log.d("mgkit", "VIEWMODEL GET IS SUCCES" + repositoryROOM.isGetTypeKinoItem.value)
            // stateFlowTypeKino.emit(repositoryAPI.getType().value.second)
            // Log.d("mgkit","INIT" + stateFlowPoster.value.toString())
//            repositoryAPI.getType().collect {
//                stateFlowTypeKino.emit(it.second)
//                repositoryROOM.insertTypeKinoItem(it.second)
//            }

            repositoryROOM.getAllTypeKinoItem().collect {
                stateFlowTypeKino.emit(it)
            }
//                if (repositoryROOM.isGetTypeKinoItem.value)
//                {
//                    // stateFlowTypeKino.emit(repositoryAPI.getType().value.second)
//                    //  repositoryROOM.insertTypeKinoItem(stateFlowTypeKino.value)
//                    Log.d("mgkit", "INITROOMGET")
//                }
//                else
//                {
//                    Log.d("mgkit", "INITAPIGET")
//                    // stateFlowTypeKino.emit(repositoryROOM.getAllTypeKinoItem().value)
//                }
        }
    }

        fun getPersonItem(idMovie : String)
        {
            val item = repositoryAPI.getPerson(idMovie).value
            if (item != null)
            {
                scope.launch {
                    stateFlowPeronItem.emit(item)
                }
            }
        }
}