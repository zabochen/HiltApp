package ua.ck.taras.hiltapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ua.ck.taras.hiltapp.data.network.NetworkService
import ua.ck.taras.hiltapp.data.network.NetworkServiceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNetworkService(
        networkServiceImpl: NetworkServiceImpl
    ): NetworkService
}