package com.example.kotlinlearning.data.cachedatasource.impl

import com.example.kotlinlearning.data.cachedatasource.CacheDataSource
import com.example.kotlinlearning.data.entity.User

/**
 * https://claude.ai/chat/af2adffb-d088-4fef-b540-d2af90e77d1b
 * https://claude.ai/public/artifacts/2fad9608-bf9f-4a57-8276-1dcec863abac
 * https://claude.ai/public/artifacts/2fad9608-bf9f-4a57-8276-1dcec863abac
 *
 * */

class MemoryCacheDataSource :CacheDataSource {
    private var cachedUsers: List<User>? = null

    private var userCache: MutableMap<Int,User> = mutableMapOf()

    private var lastCacheTime: Long = 0

    private val cacheDuration = 5*(60 * 1000) // 10 minutes in milliseconds


    override suspend fun getUsers(): List<User> {
        return cachedUsers?.takeIf { isExpired() } ?: emptyList()
    }


    override suspend fun getUser(id: Int): User {
        val user = cachedUsers?.get(id)
        if (user == null || isExpired()) {
            throw IllegalStateException("User not found or data expired")
        }
        return user
    }

    override suspend fun putUsers(users: List<User>) {
        cachedUsers=users
        users.forEach{userCache[it.uid]  = it}
        lastCacheTime=System.currentTimeMillis()
    }

    override suspend fun putUser(user: User) {
        userCache[user.uid]=user
        cachedUsers=cachedUsers?.map { if (it.uid == user.uid) user else it }
        lastCacheTime=System.currentTimeMillis()
    }

    override suspend fun clearCache() {
        cachedUsers=null
        userCache.clear()
        lastCacheTime=0

    }

    override suspend fun isExpired(): Boolean {
        return System.currentTimeMillis() - lastCacheTime > cacheDuration
    }
}