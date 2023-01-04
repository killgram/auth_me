package com.example.authme.network.r3d3

import com.example.authme.network.GetLoginModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetLogin {
    @GET("/r3d3/getLogin")
    suspend fun getLogin(
        @Query("key") key: String
    ): Response<GetLoginModel>
}

