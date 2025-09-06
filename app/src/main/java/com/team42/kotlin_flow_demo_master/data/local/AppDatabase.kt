package com.team42.kotlin_flow_demo_master.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.team42.kotlin_flow_demo_master.model.User

/**
 * Project: Kotlin-Flow-Demo-Master
 * File: AppDatabase.kt
 * Created By: ANIL KUMAR on 9/6/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}