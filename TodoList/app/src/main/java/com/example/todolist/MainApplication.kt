package com.example.todolist

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.todolist.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainApplication: Application() {
    private var database: AppDatabase? = null
    private var apiService: ApiService? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "todo_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    fun getDatabase(): AppDatabase? {
        return database
    }
    fun getApiService(): ApiService? {
        return apiService
    }
    companion object {
        lateinit var instance: MainApplication
    }

}