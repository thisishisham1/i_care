package com.example.icare.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icare.model.classes.apiClass.ClinicReservation
import com.example.icare.model.classes.apiClass.UserResponse

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(reservation: ClinicReservation)

    @Query("SELECT * FROM reservation_users")
    suspend fun getReservation(): List<ClinicReservation>

    @Query("DELETE from reservation_users")
    suspend fun clearReservation()
}