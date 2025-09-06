package com.team42.kotlin_flow_demo_master.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team42.kotlin_flow_demo_master.model.User
import com.team42.kotlin_flow_demo_master.util.UiState

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.users.collectAsState()

    when (state) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> UserList((state as UiState.Success<List<User>>).data)
        is UiState.Error -> Text("Error: ${(state as UiState.Error).message}")
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            Text(user.name, modifier = Modifier.padding(16.dp))
        }
    }
}