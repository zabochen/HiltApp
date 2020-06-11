package ua.ck.taras.hiltapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import ua.ck.taras.hiltapp.model.PostItem

interface NetworkService {

    @GET(value = "posts")
    suspend fun fetchPosts(): Response<List<PostItem>>
}