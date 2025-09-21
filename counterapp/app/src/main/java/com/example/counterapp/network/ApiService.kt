package com.example.counterapp.network

import com.example.counterapp.model.LoginRequest
import com.example.counterapp.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
