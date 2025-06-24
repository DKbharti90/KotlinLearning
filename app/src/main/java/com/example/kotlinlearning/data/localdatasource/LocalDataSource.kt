package com.example.kotlinlearning.data.localdatasource

import com.example.kotlinlearning.data.entity.User

interface LocalDataSource {

    suspend fun getAllUsers(): List<User>
    suspend fun getUserById( id: Int) : User?
    suspend fun insertUsers(users : List<User>)
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(id: Int)
    suspend fun clearUser()
}