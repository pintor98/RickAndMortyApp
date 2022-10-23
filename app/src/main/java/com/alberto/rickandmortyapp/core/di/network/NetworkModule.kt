package com.alberto.rickandmortyapp.core.di.network

import android.content.Context
import com.alberto.rickandmortyapp.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesHost(builder: Retrofit.Builder): Retrofit =
        builder.baseUrl(BuildConfig.BASE_URL).build()

    @Provides
    fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        logging: HttpLoggingInterceptor,
        client: OkHttpClient.Builder,
        @ApplicationContext context: Context,
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return client
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    .addHeader("lang", Locale.getDefault().language)
                    .addHeader("App-Version", BuildConfig.VERSION_NAME)
                    .addHeader("App-Build", BuildConfig.VERSION_CODE.toString())
                    .build()
                    .let(chain::proceed)
            }
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }
}