package com.example.kotlinlearning.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Module(@PrimaryKey(autoGenerate = true) val id: Int, val name: String,val navigation:String)
