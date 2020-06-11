package ua.ck.taras.hiltapp.data.network

import javax.inject.Inject

class NetworkServiceImpl @Inject constructor() : NetworkService {
    override fun getResponse(): String {
        return "Success network response"
    }
}