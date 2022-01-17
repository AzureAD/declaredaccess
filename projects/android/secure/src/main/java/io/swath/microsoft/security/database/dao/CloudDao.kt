package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.swath.microsoft.security.database.entities.Cloud

@Dao
interface CloudDao {
    @Query("SELECT * FROM Cloud")
    fun getAll():List<Cloud>
}