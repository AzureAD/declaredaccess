package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.swath.microsoft.security.database.entities.Application

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM Application")
    fun getAll():List<Application>
}