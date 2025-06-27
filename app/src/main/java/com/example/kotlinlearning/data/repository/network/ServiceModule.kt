package com.example.kotlinlearning.data.repository.network

import com.example.kotlinlearning.data.entity.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response


interface ServiceModule {


    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<User>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>


    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>

    @PUT("users/{id}")
    suspend fun updateUser(@Body user: User): Response<User>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}