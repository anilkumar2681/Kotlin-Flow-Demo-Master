package com.team42.kotlin_flow_demo_master.util

/**
 * Project: Kotlin-Flow-Demo-Master
 * File: AppDatabase.kt
 * Created By: ANIL KUMAR on 9/6/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}