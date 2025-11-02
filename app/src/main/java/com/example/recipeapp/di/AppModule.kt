package com.example.recipeapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.database.RecipeDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

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
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
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
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi {
        return retrofit.create(RecipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(Constants.USER_PREFERENCES) }
        )
    }

//    @Provides
//    @Singleton
//    fun provideVideoPlayer(app: Application): Player {
//        return ExoPlayer.Builder(app)
//            .build()
//    }
}