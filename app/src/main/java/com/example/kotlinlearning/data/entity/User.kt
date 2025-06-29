package com.example.kotlinlearning.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    val uid: Int,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
){
    fun toEntity(): UserEntity {
        return UserEntity(
            uid = this.uid,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password
        )
    }

}
