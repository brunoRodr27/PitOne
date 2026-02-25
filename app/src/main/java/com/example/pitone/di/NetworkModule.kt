package com.example.pitone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.Logging
import javax.inject.Singleton
import android.util.Log.d
import com.example.pitone.data.remote.OpenF1ApiService
import com.example.pitone.data.remote.OpenF1ApiServiceImpl
import io.ktor.client.plugins.logging.LogLevel

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                    encodeDefaults = true
                })
            }
            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) {
                        d("Ktor", message)
                    }
                }
                level = LogLevel.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideOpenF1Api(client: HttpClient): OpenF1ApiService{
        return OpenF1ApiServiceImpl(client)
    }

}