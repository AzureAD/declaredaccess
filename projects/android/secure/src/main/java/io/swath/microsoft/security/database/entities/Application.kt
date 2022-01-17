package io.swath.microsoft.security.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Application(
    @ColumnInfo(name="ClientId") @PrimaryKey val clientId: String,
    @ColumnInfo(name="Name") val name: String,
    @ColumnInfo(name="Version") val version: String
)