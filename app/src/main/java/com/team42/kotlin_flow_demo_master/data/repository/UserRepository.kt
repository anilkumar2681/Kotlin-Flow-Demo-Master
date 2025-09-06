package com.team42.kotlin_flow_demo_master.data.repository

import android.content.Context
import com.team42.kotlin_flow_demo_master.data.local.UserDao
import com.team42.kotlin_flow_demo_master.data.remote.UserApi
import com.team42.kotlin_flow_demo_master.model.User
import com.team42.kotlin_flow_demo_master.util.UiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val dao: UserDao,
) {
    fun getUsers(): Flow<UiState<List<User>>> = flow {
        emit(UiState.Loading)
        try {
            val cached = dao.getUsers()
            if (cached.isNotEmpty()) emit(UiState.Success(cached))

            val remote = api.getUsers()
            dao.insertUsers(remote)
            emit(UiState.Success(remote))
        } catch (e: Exception) {
            emit(UiState.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}