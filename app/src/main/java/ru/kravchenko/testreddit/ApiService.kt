package ru.kravchenko.testreddit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/top.json")
    fun getData(): Call<Result>
}