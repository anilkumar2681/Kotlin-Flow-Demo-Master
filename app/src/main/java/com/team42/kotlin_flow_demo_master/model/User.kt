package com.team42.kotlin_flow_demo_master.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Project: Kotlin-Flow-Demo-Master
 * File: AppDatabase.kt
 * Created By: ANIL KUMAR on 9/6/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/

object DbConfig {
    const val TABLE_NAME = "users"
}

@Entity(tableName = DbConfig.TABLE_NAME)
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val company: String,
    val username: String,
    val email: String,
    val address: String,
    val zip: String,
    val state: String,
    val country: String,
    val phone: String,
    val photo: String? = null
)