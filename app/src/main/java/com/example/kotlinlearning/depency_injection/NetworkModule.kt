package com.example.kotlinlearning.depency_injection

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.kotlinlearning.data.repository.network.ApiService
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your base URL
            .client(provideOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provodeApolooClinet(): ApolloClient{
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(provideOkHttp())
            .build()
    }

    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor({ chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(
                    original.method,
                    original.body
                )
                builder.addHeader("Authorization", "Bearer " + "BuildConfig.AUTH_TOKEN")
                chain.proceed(builder.build())
            }).build()

    }





}