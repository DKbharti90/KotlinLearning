package com.example.kotlinlearning.data.remot.impl

import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.remot.RemoteDataSource
import com.example.kotlinlearning.data.repository.network.ApiService
import retrofit2.Response

class ApiDataSource(private val serviceModule: ApiService) :  RemoteDataSource{

    override suspend fun getUsers(): Response<List<User>> =serviceModule.getUsers()

    override suspend fun getUser(id: Int): Response<User> =serviceModule.getUser(id)

    override suspend fun createUser(user: User): Response<User> =serviceModule.createUser(user)

    override suspend fun updateUser(user: User): Response<User> =serviceModule.updateUser(user)

    override suspend fun deleteUser(id: Int): Response<Unit>  =serviceModule.deleteUser(id)
}