package com.example.kotlinlearning.depency_injection

import com.example.kotlinlearning.data.repository.network.ApiService
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.data.cachedatasource.CacheDataSource
import com.example.kotlinlearning.data.cachedatasource.impl.MemoryCacheDataSource
import com.example.kotlinlearning.data.localdatasource.LocalDataSource
import com.example.kotlinlearning.data.localdatasource.impl.DatabaseDataSource
import com.example.kotlinlearning.data.remot.RemoteDataSource
import com.example.kotlinlearning.data.remot.impl.ApiDataSource
import com.example.kotlinlearning.data.repository.shared.UserRepository
import com.example.kotlinlearning.data.repository.shared.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideRemortDataSource(apiService: ApiService): RemoteDataSource{
        return ApiDataSource(apiService)
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(database: AppDatabase) : LocalDataSource{
        return DatabaseDataSource(database.userDao())
    }

    @Provides
    @Singleton
    fun provideCacheDatasource(): CacheDataSource{
        return MemoryCacheDataSource()
    }

    @Provides
    @Singleton
    fun provideUserRepository(localDataSource: LocalDataSource,
                              cacheDataSource: CacheDataSource,
                              remoteDataSource: RemoteDataSource):
            UserRepository{
        return UserRepositoryImpl(remoteDataSource,localDataSource,cacheDataSource)
    }

}