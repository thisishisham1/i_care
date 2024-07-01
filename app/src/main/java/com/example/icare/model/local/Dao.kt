package com.example.icare.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icare.model.classes.UserResponse

@Dao
interface UserResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userResponse: UserResponse)

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): UserResponse

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()

    @Query("SELECT COUNT(*) FROM user_table")
    suspend fun getUserCount(): Int
}