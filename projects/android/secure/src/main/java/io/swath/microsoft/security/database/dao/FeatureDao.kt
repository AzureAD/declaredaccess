package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.swath.microsoft.security.database.entities.Feature

@Dao
interface FeatureDao {
    @Query("SELECT * FROM Feature")
    fun getAll():List<Feature>
}