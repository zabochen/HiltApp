package ua.ck.taras.hiltapp.repository

import ua.ck.taras.hiltapp.data.network.NetworkService
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun fetchNetworkData() = networkService.fetchPosts()
}