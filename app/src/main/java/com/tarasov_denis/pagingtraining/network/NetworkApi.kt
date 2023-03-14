package com.tarasov_denis.pagingtraining.network

import com.tarasov_denis.pagingtraining.Repository
import com.tarasov_denis.pagingtraining.data.ListCharacters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET("users/{username}/repos")
    suspend fun fetchRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): List<Repository>


    @GET("users/{username}/repos")
    suspend fun fetchCharacters(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): List<Repository>


    @GET("character/")  // page{number
    suspend fun getCharactersPage(@Query("page") number: Int) : ListCharacters


}