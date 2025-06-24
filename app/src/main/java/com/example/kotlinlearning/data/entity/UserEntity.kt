package com.example.kotlinlearning.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?
){
    fun toDto(): User {
        return User(
            uid = this.uid,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password
        )
    }

}