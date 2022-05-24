package com.minhkhue.runningtracker.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.minhkhue.runningtracker.model.local.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateNote(user: User)

    @Query("SELECT * FROM User ORDER BY idUser DESC")
    fun getUserInfo(): LiveData<List<User>>
}