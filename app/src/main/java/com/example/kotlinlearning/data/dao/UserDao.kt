package com.example.kotlinlearning.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinlearning.data.entity.User
import com.example.kotlinlearning.data.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM userentity WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<UserEntity>

    @Query("SELECT * FROM userentity WHERE uid = (:userId)")
    suspend fun loadAllById(userId: Int): UserEntity?

    @Query("SELECT * FROM userentity WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    suspend fun findByName(first: String, last: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM UserEntity WHERE uid = :id")
    suspend fun deleteUser(id: Int)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("DELETE FROM userentity")
    suspend fun clearUsers()
}