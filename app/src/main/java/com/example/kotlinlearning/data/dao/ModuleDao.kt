package com.example.kotlinlearning.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinlearning.data.entity.Module

@Dao
interface ModuleDao {

    @Query("SELECT * FROM Module")
    fun getAll(): List<Module>

    @Query("SELECT * FROM Module ORDER BY name COLLATE NOCASE ASC")
    fun allModuleByName(): PagingSource<Int, Module>

    @Insert
    fun insert(module: List<Module>)

    @Insert
    fun insert(module: Module)

    @Delete
    fun delete(module: Module)
}