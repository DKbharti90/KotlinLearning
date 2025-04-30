package com.example.kotlinlearning.data.repository.network

import javax.inject.Inject

class ServiceModuleImpl @Inject constructor() : ServiceModule {

    override fun getData(): String {
        return "Hello"
    }
}