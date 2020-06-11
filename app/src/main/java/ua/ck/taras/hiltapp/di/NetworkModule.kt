package ua.ck.taras.hiltapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.ck.taras.hiltapp.data.network.ConnectionStateInterceptor
import ua.ck.taras.hiltapp.data.network.NetworkService
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkService(
        connectionStateInterceptor: ConnectionStateInterceptor
    ): NetworkService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(connectionStateInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }
}