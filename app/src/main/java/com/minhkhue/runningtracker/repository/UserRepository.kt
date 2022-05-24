package com.minhkhue.runningtracker.repository

import androidx.lifecycle.LiveData
import com.minhkhue.runningtracker.data.local.database.UserDao
import com.minhkhue.runningtracker.model.local.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) : IUserRepository {
    private val ioDispatcher = Dispatchers.IO
    override suspend fun insertUser(user: User) {
        withContext(ioDispatcher) {
            userDao.insertUser(user)
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(ioDispatcher) {
            userDao.updateNote(user)
        }
    }

    override fun getUserInfo(): LiveData<List<User>> = userDao.getUserInfo()
}
