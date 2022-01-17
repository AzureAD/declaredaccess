package io.swath.microsoft.security.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @ColumnInfo(name="Id") @PrimaryKey val id: Int,

    @ColumnInfo(name="PreferredUsername") val preferredUsername: String,
    @ColumnInfo(name="UniqueIdentifier") val uniqueIdentifier:String,
    @ColumnInfo(name="Ownership") val ownership: Int
)