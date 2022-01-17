package io.swath.microsoft.security.database.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cloud(
    @ColumnInfo(name="Id") @PrimaryKey val id: Int,
    @ColumnInfo(name="Name") val name: String,
    @ColumnInfo(name="RootAuthority") val rootAuthority: String
)