package com.bedirhandroid.pokemontechcase.api

import com.bedirhandroid.pokemontechcase.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//timeout sec.
const val API_TIMEOUT = 15L

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {
    private var retrofit: ApiService? = null

    @Singleton
    @Provides
    //init client with base url
    fun getClient(): ApiService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build()
                .create(ApiService::class.java)
        }
        return retrofit as ApiService
    }

    private fun client(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            callTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            this.build()
        }
    }
}