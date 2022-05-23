package com.minhkhue.runningtracker.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val weight: Int,
    val phoneNumber: String,
    val avatar: String
)