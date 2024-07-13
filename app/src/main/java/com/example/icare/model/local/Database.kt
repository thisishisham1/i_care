package com.example.icare.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.icare.model.classes.apiClass.ClinicReservation
import com.example.icare.model.classes.apiClass.UserResponse

@Database(entities = [UserResponse::class, ClinicReservation::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userResponseDao(): UserResponseDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}