package io.swath.microsoft.security.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.swath.microsoft.security.database.entities.Account

@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    fun getAll():List<Account>
}