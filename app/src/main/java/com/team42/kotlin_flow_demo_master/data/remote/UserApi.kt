package com.team42.kotlin_flow_demo_master.data.remote


import com.team42.kotlin_flow_demo_master.model.User
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}