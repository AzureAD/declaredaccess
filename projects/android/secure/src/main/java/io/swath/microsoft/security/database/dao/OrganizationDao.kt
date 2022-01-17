package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.swath.microsoft.security.database.entities.Organization

@Dao
interface OrganizationDao {
    @Query("SELECT * FROM Organization")
    fun getAll():List<Organization>
}