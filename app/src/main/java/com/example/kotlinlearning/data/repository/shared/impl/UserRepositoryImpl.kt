package com.example.kotlinlearning.data.repository.shared.impl

import androidx.room.Query
import com.example.kotlinlearning.data.cachedatasource.CacheDataSource
import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.localdatasource.LocalDataSource
import com.example.kotlinlearning.data.remot.RemoteDataSource
import com.example.kotlinlearning.data.repository.shared.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val cacheDataSource: CacheDataSource
) : UserRepository {


    override suspend fun getUsers(forceRefresh: Boolean): Result<List<User>> {

        return try {

            // 1. Check cache first (if not forcing refresh)
            if (!forceRefresh) {
                cacheDataSource.getUsers().let { cachedUser ->
                    return Result.success(cachedUser)
                }
            }

            val localUser = localDataSource.getAllUsers()
            if (localUser.isNotEmpty() && !forceRefresh) {
                cacheDataSource.putUsers(localUser)
                return Result.success(localUser)
            }

            val response = remoteDataSource.getUsers()
            if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                localDataSource.clearUser()
                localDataSource.insertUsers(users)
                cacheDataSource.putUsers(users)
                Result.success(users)
            } else {
                if (localUser.isNotEmpty()) {
                    Result.success(localUser)
                    Result.success(localUser)
                } else {
                    Result.failure(Exception("HTTP ${response.code()} : ${response.message()}"))
                }
            }
        } catch (exception: Exception) {
            val localUser = localDataSource.getAllUsers()
            if (localUser.isNotEmpty()) {
                Result.success(localUser)
            } else {
                Result.failure(exception)
            }
        }

    }

    override suspend fun getUser(id: Int, forceRefresh: Boolean): Result<User> {

        return try {
            if (!forceRefresh){
                cacheDataSource.getUser(id).let { cacheduser-> return Result.success(cacheduser) }
            }

            localDataSource.getUserById(id)?.let { localuser->
                if (!forceRefresh){
                    cacheDataSource.putUser(localuser)
                    Result.success(localuser)
                }
            }

            val response=remoteDataSource.getUser(id)
            if (response.isSuccessful){
                val user=response.body()!!
                // Save to database and cache
                localDataSource.insertUser(user)
                cacheDataSource.putUser(user)
                Result.success(user)
            }else{
                localDataSource.getUserById(id)?.let { Result.success(it) }?: Result.failure(Exception("User not found"))
            }

        }catch (exception: Exception){
            localDataSource.getUserById(id)?.let { Result.success(it) } ?:Result.failure(exception)
        }
    }

    override suspend fun searchUser(query: Query): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User): Result<User> {
        return try {
            val response=remoteDataSource.createUser(user);
            if (response.isSuccessful){
                val createdUser = response.body()!!
                localDataSource.insertUser(createdUser)
                cacheDataSource.putUser(createdUser)
                Result.success(createdUser)
            }else{
                Result.failure(Exception("Failed to create the user ${response.message()}"))
            }
        }catch (exception :Exception){
            Result.failure(exception)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val response=remoteDataSource.updateUser(user)
            if (response.isSuccessful){
                val updatedUser=response.body()!!
                localDataSource.updateUser(updatedUser)
                cacheDataSource.putUser(updatedUser)
                Result.success(updatedUser)
            }else{
                Result.failure(Exception("Failed to update the User ${response.message()}"))
            }
        }catch (exception : Exception){
            Result.failure(exception)
        }
    }

    override suspend fun deleteUser(id: Int): Result<Boolean> {
       /* return try {

        }
        catch (exception : Exception){
            Result.failure(exception)
        }*/

        return  Result.success(true);
    }

    override suspend fun refreshUser(): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearCache() {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersFlow(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFlow(): Flow<User> {
        TODO("Not yet implemented")
    }
}

