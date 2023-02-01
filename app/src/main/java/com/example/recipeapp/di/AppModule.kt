package com.example.recipeapp.di

import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.repository.HomeRepositoryImpl
import com.example.recipeapp.domain.repository.HomeRepository
import com.example.recipeapp.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()

            val requestBody = original.newBuilder()
                .header(Constants.HEADER_API_KEY, BuildConfig.HEADER_API_KEY)
                .header(Constants.HEADER_API_HOST, BuildConfig.HEADER_API_HOST)
                .method(original.method, original.body)
                .build()

            chain.proceed(requestBody)
        })
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi =
        retrofit.create(RecipeApi::class.java)

    @Provides
    fun provideHomeRepository(recipeApi: RecipeApi): HomeRepository =
        HomeRepositoryImpl(recipeApi = recipeApi)
}