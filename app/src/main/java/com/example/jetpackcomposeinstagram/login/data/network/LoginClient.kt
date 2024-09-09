package com.example.jetpackcomposeinstagram.login.data.network

import com.example.jetpackcomposeinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {

    @GET("v3/1208d165-3848-4540-ab29-61d208d60481")
    suspend fun doLogin(): Response<LoginResponse>
}