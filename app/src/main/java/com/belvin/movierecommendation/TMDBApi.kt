package com.belvin.movierecommendation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie")
    fun getMoviesResults(
        @Query("api_key") api_key:String,
        @Query("query") query:String
    ): Call<SearchResult>
}