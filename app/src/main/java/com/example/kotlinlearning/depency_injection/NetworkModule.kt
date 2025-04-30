package com.example.kotlinlearning.depency_injection

import com.example.kotlinlearning.data.repository.network.ServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindNetworkService(impl: ServiceModule): ServiceModule

}