package com.example.kotlinlearning.data.remot

import com.example.kotlinlearning.data.entity.User
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getUsers() : Response<List<User>>
    suspend fun getUser(id: Int) : Response<User>
    suspend fun createUser(user: User) : Response<User>
    suspend fun updateUser(user: User) : Response<User>
    suspend fun deleteUser(int: Int) : Response<Unit>
}