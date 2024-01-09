package com.example.applicationkino.Repository

import android.util.Log
import com.example.applicationkino.ErrorList
import com.example.applicationkino.IRepositoryAPI
import com.example.applicationkino.Model.Random.Random
import com.example.applicationkino.ModelSearch.ModelSearch
import com.example.applicationkino.Person.Person
import com.example.applicationkino.Service.ResponsesService
import com.example.applicationkino.TypeKino
import com.example.applicationkino.TypeKinoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class APIRepository @Inject constructor() : IRepositoryAPI{

    private val scope = CoroutineScope(Dispatchers.IO)
    companion object
    {
        const val BASE_URL = "https://api.kinopoisk.dev/"
        const val IMAGE_URL = "https://avatars.mds.yandex.net/"
        const val API_KEY = "0GZ5N19-MXDM79J-HCK4ADD-WTQ3CZ1"
    }

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    private val responseService = retrofit.create(ResponsesService::class.java)
    fun getMovieFromId() {
        val result = responseService.getMovieFromId(API_KEY, 1)
        //val stateFlow = MutableStateFlow<Afisha?>(null)
        scope.launch {
            val res = result.execute()
            Log.d("mgkit", "Res" + res.toString())
            if (res.isSuccessful)
            {
                val body= res.body()
                Log.d("mgkit", body.toString())
            }
        }
    }


    fun getMovieRandom() : MutableStateFlow<Random?> {
        val result = responseService.getRandomMovie(API_KEY, 10, 5, "type = movie")
        val stateFlow = MutableStateFlow<Random?>(null)
        scope.launch {
            val res = result.execute()
            Log.d("mgkit", "Res" + res.toString())
            if (res.isSuccessful)
            {
                val body= res.body()
                if (body == null)
                {
                    stateFlow.emit(null)

                }
                else
                {
                    stateFlow.emit(body)
                    Log.d("mgkit", "BODYGET-" + body.toString())
                }
                Log.d("mgkit", "RANDOM-" + body.toString())
            }
            else
            {
                stateFlow.emit(null)
            }
        }
        return stateFlow
    }


    fun getMovieSearch(countriesName : String?, rating : String?, sortField: String?, sortType : String?, ticketsOnSale : String?) : StateFlow<Pair<ErrorList, ModelSearch?>> {
        Log.d("mgkit", "ЗАПУСК")
        val result = responseService.getMovieSearch(API_KEY, countriesName, rating, sortField, sortType, ticketsOnSale)
        val stateFlow = MutableStateFlow<Pair<ErrorList, ModelSearch?>>(ErrorList.WAIT to null)
        scope.launch {
            try {
                val res = result.execute()
                Log.d("mgkit", res.toString())
                if (res.isSuccessful)
                {
                    val body= res.body()
                    if (body == null)
                    {
                        stateFlow.emit(ErrorList.ERROR to null)
                    }
                    else {

                        body.docs.map { it.poster.byte = imageService.downloadImage(it.poster.previewUrl).execute().body()?.bytes() }
                        stateFlow.emit(ErrorList.OK202 to body)
                    }
                }
                else
                {
                    stateFlow.emit(ErrorList.ERROR to null)
                }
            }
            catch (e : Exception)
            {
                stateFlow.emit(ErrorList.ERROR to null)
            }
        }
        return stateFlow
    }

    private val retrofitImage = Retrofit.Builder().baseUrl(IMAGE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    fun getType() : MutableStateFlow<Pair<ErrorList, List<TypeKinoItem>?>>
    {
        val result = responseService.getTypeKino(API_KEY)
        Log.d("mgkit", result.toString())
        val stateFlow = MutableStateFlow<Pair<ErrorList, List<TypeKinoItem>?>>(ErrorList.WAIT to null)
        scope.launch {
            try {
                val res = result.execute()
                Log.d("mgkit", res.toString())
                if (res.isSuccessful)
                {
                    val body = res.body()
                    Log.d("mgkit", body.toString())
                    if (body == null)
                    {
                        stateFlow.emit(ErrorList.ERROR to null)
                    }
                    else
                    {
                        Log.d("mgkit", body.toString())
                        stateFlow.emit(ErrorList.OK202 to body.map { it })
                    }
                }
                else
                {
                    stateFlow.emit(ErrorList.ERROR to null)
                }
            }
            catch (ex : Exception)
            {
                stateFlow.emit(ErrorList.ERROR to null)
            }
        }
        return stateFlow
    }

    fun getPerson(idMovie : String) : MutableStateFlow<Person?>
    {
        val result = responseService.getPerson(API_KEY, idMovie)
        val stateFlow = MutableStateFlow<Person?>(null)
        scope.launch {
            try {
                val res = result.execute()
                if (res.isSuccessful)
                {
                    val body = res.body()
                    if (body == null)
                    {
                        stateFlow.emit(null)
                    }
                    else
                    {
                        stateFlow.emit(body)
                    }
                }
                else
                {
                    stateFlow.emit(null)
                }
            }
            catch (ex : Exception)
            {
                stateFlow.emit(null)
            }
        }
        return stateFlow
    }


    private val imageService = retrofitImage.create(ResponsesService::class.java)

    fun getImage(string: String) : StateFlow<ByteArray?> {
        val result = imageService.downloadImage(string)
        Log.d("mgkit", "result${result.isExecuted}")
        val stateFlow = MutableStateFlow<ByteArray?>(null)
        scope.launch {
            try {
                val res = result.execute()
                if (res.isSuccessful)
                {
                    val body= res.body()
                    if (body == null)
                    {
                        stateFlow.emit(null)
                    }
                    else
                    {
                        // val list = body.docs
                        stateFlow.emit(body.byteStream().readBytes())
                        Log.d("mgkit", "STATEFLOWEMITBYTES"+ body.bytes().toString())
                    }
                }
                else
                {
                    stateFlow.emit(null)
                }
            }
            catch (e : Exception)
            {
                stateFlow.emit(null)
            }
        }
        return stateFlow
    }
}