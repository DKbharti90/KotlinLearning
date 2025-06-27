package com.example.kotlinlearning.data.cachedatasource

import com.example.kotlinlearning.data.entity.User

interface CacheDataSource {
    suspend fun getUsers() : List<User>
    suspend fun getUser(id : Int) : User
    suspend fun putUsers(users: List<User>)
    suspend fun putUser(user: User)
    suspend fun clearCache()
    suspend fun isExpired(): Boolean
}