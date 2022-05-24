package com.minhkhue.runningtracker.repository

import androidx.lifecycle.LiveData
import com.minhkhue.runningtracker.model.local.User

interface IUserRepository {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    fun getUserInfo(): LiveData<List<User>>
}