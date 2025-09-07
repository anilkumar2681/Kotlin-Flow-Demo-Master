package com.team42.kotlin_flow_demo_master.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.team42.kotlin_flow_demo_master.model.User
import com.team42.kotlin_flow_demo_master.util.UiState
import com.team42.kotlin_flow_demo_master.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.users.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFEDE7F6), // light lavender top
            Color(0xFFD1C4E9)  // slightly darker bottom
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Purple background
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .background(backgroundBrush)) {
            when (state) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    UserList((state as UiState.Success<List<User>>).data)
                }

                is UiState.Error -> {
                    ErrorScreen(
                        message = (state as UiState.Error).message,
                        onRetry = { viewModel.loadUsers() }
                    )
                }
            }
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: User) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(gradientBrush)
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile photo (left side)
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(user.photo)
                    .placeholder(R.drawable.ic_placeholder) // placeholder
                    .error(R.drawable.ic_placeholder)       // fallback
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = "Profile photo of ${user.name}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Text (right side)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Email: ${user.email}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
                Text(
                    text = "Company: ${user.company}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
                Text(
                    text = "Phone: ${user.phone}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
            }
        }
    }
}

