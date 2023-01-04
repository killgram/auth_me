package com.example.authme.network.r3d3

import com.example.authme.network.GetLoginModel
import com.example.authme.network.SetPasswordModel
import com.example.authme.network.SetPasswordRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GetLogin {
    @GET("/r3d3/getLogin")
    suspend fun getLogin(
        @Query("key") key: String
    ): Response<GetLoginModel>
}

interface SetPassword {
    @POST("/r3d3/setPassword")
    suspend fun setPassword(
        @Body data: SetPasswordRequestModel
    ): Response<SetPasswordModel>
}