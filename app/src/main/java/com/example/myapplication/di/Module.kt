package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.data.db.AppDataBase.Companion.DATABASE_NAME
import com.example.myapplication.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context,
            AppDataBase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun getApiService(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
        val movieApiService = retrofit.create(ApiService::class.java)
        return movieApiService
    }


}