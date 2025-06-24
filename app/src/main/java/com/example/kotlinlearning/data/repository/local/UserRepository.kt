package com.example.kotlinlearning.data.repository.local

import com.example.kotlinlearning.data.ApiService
import com.example.kotlinlearning.data.dao.UserDao
import com.example.kotlinlearning.data.entity.Module
import com.example.kotlinlearning.data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val  webService: ApiService) {
    fun insert(user: User)=userDao.insert(user)
    fun delete(user: User)=userDao.delete(user)
    fun getAll(): List<User> = userDao.getAll()
}