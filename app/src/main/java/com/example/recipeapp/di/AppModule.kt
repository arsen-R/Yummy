package com.example.recipeapp.di

import android.app.Application
import androidx.room.Room
import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.database.RecipeDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.domain.repository.RecipeRepository
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
    fun provideRecipeDatabase(application: Application): RecipeDatabase {
        return Room.databaseBuilder(
            application,
            RecipeDatabase::class.java,
            Constants.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao {
        return recipeDatabase.recipeDao()
    }
    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi =
        retrofit.create(RecipeApi::class.java)

    @Provides
    fun provideHomeRepository(recipeApi: RecipeApi, recipeDao: RecipeDao): RecipeRepository =
        RecipeRepositoryImpl(recipeApi = recipeApi, recipeDao = recipeDao)
}