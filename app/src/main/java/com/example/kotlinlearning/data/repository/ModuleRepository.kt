package com.example.kotlinlearning.data.repository

import androidx.paging.PagingSource
import com.example.kotlinlearning.data.dao.ModuleDao
import com.example.kotlinlearning.data.entity.Module
import javax.inject.Inject

class ModuleRepository @Inject constructor( private val moduleDao:ModuleDao) {

    fun insert(module: Module)=moduleDao.insert(module)
    fun insert(modules: List<Module>)=moduleDao.insert(modules)
    fun delete(module: Module)=moduleDao.delete(module)
    fun allModuleByName():PagingSource<Int, Module> =moduleDao.allModuleByName()
    fun getAll(): List<Module> = moduleDao.getAll()
}