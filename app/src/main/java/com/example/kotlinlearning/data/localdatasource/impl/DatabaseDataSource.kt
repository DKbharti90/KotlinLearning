package com.example.kotlinlearning.data.localdatasource.impl

import com.example.kotlinlearning.data.dao.UserDao
import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.localdatasource.LocalDataSource

class DatabaseDataSource (private val useDao: UserDao): LocalDataSource {
    override suspend fun getAllUsers(): List<User> {
        return useDao.getAll().map { it.toDto() }
    }

    override suspend fun getUserById(id: Int): User? {
        return useDao.loadAllById(id)?.toDto()
    }

    override suspend fun insertUsers(users: List<User>) {
        return useDao.insertUsers(users.map { it.toEntity() })
    }

    override suspend fun insertUser(user: User) {
        return useDao.insert(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        return useDao.updateUser(user.toEntity())
    }

    override suspend fun deleteUser(id: Int) {
       return useDao.deleteUser(id)
    }

    override suspend fun clearUser() {
        return useDao.clearUsers()
    }
}