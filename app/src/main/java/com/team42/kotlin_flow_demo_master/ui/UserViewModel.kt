package com.team42.kotlin_flow_demo_master.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team42.kotlin_flow_demo_master.data.repository.UserRepository
import com.team42.kotlin_flow_demo_master.model.User
import com.team42.kotlin_flow_demo_master.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val users: StateFlow<UiState<List<User>>> = _users

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            repository.getUsers().collect { _users.value = it }
        }
    }
}