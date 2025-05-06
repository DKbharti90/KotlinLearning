package com.example.kotlinlearning.depency_injection

import com.example.kotlinlearning.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.example.com/"

   /* @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient  {
       val keyStore= KeyStore.getInstance("MyKeyStore")
        val inputStraeme=context.resources.openRawResource(R.raw.your_certificate)
        keyStore.load(inputStraeme,password.tocharArray())
        val trustmanagerfactory= TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustmanagerfactory.init(keyStore)
        val sslContext= SSLContext.getInstance("TLS")
        sslContext.init(null,trustmanagerfactory.trustManagers,null)
        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustmanagerfactory.trustManagers[0] as X509TrustManager)
            .addInterceptor(SSLInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    }*/

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }





}