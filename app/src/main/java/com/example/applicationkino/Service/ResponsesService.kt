package com.example.applicationkino.Service

import com.example.applicationkino.Model.Random.Random
import com.example.applicationkino.ModelSearch.ModelSearch
import com.example.applicationkino.Person.Person
import com.example.applicationkino.TypeKino
import com.example.applicationkino.UserInterface.Afisha
import kotlinx.coroutines.flow.StateFlow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ResponsesService {
    @GET("/v1.4/movie/{id}")
    fun getMovieFromId(
        @Header("X-API-KEY") apiKey: String,
        @Path("id") movieId: Int
    ) : Call<Afisha>

    @GET("/v1.4/movie/search")
    fun getRandomMovie(@Header("X-API-KEY") apiKey: String, @Query("page") page : Int, @Query("limit") limit : Int, @Query("query") query  : String) : Call<Random>

    @GET("/v1.4/movie")
    fun getMovieSearch(@Header("X-API-KEY") apiKey: String, @Query("countries.name") name : String? = "Россия", @Query("rating.kp") rating : String?, @Query("sortField") sortField : String?, @Query("sortType") sortType : String?, @Query("ticketsOnSale") ticketsOnSale : String?) : Call<ModelSearch>

    @GET
    fun downloadImage(@Url url: String) : Call<ResponseBody>

    @GET("/v1.4/movie")
    fun getMovieTop10(@Header("X-API-KEY") apiKey: String, @Query("countries.name") name : String = "Россия", @Query("rating.kp") rating : String = "8-10", @Query("sortField") sortField : String = "votes.russianFilmCritics", @Query("sortType") sortType : String = "-1")


    @GET("/v1.4/person")
    fun getPerson(
        @Header("X-API-KEY") apiKey: String,
        @Query("movies.id") idMovies : String,
    ) : Call<Person>

    @GET("/v1/movie/possible-values-by-field")
    fun getTypeKino(
        @Header("X-API-KEY") apiKey : String,
        @Query("field") field: String = "genres.name"
    ) : Call<TypeKino>
}