package ua.ck.taras.hiltapp.model

import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)