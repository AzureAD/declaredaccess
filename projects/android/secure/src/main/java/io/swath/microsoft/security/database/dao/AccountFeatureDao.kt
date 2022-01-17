package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.util.TableInfo
import io.swath.microsoft.security.database.entities.Account

@Dao
interface AccountFeatureDao {
    @Query("SELECT * FROM AccountFeatureEnabled")
    fun getAll():List<Account>
}