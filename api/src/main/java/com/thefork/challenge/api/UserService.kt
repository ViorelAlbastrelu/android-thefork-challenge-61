package com.thefork.challenge.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserService {

    companion object {
        private const val APP_ID = "66747d009ed709ee2312e1b6"
    }

    @GET("user?limit=10")
    @Headers("app-id: $APP_ID")
    suspend fun getUsers(@Query("page") page: UInt): Response<Page<UserPreview>>

}
