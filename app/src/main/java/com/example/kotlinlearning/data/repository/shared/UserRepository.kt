package com.example.kotlinlearning.data.repository.shared

import androidx.room.Query
import com.example.kotlinlearning.data.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(forceRefresh:Boolean ): Result<List<User>>

    suspend fun getUser(id:Int, forceRefresh: Boolean):Result<User>

    suspend fun searchUser(query: Query) :Result<User>

    suspend fun createUser(user: User):Result<User>

    suspend fun updateUser(user: User):Result<User>

    suspend fun deleteUser(id: Int):Result<Boolean>

    suspend fun refreshUser(): Result<List<User>>

    suspend fun clearCache()

    suspend fun getUsersFlow() :Flow<List<User>>

    suspend fun getUserFlow(id: Int) :Flow<User>


}