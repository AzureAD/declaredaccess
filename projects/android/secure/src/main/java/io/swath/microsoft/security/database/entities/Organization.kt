package io.swath.microsoft.security.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Cloud::class,
    parentColumns = ["Id"],
    childColumns = ["CloudId"],
    onDelete = ForeignKey.NO_ACTION
)]
)
data class Organization(
    @ColumnInfo(name="TenantId") @PrimaryKey val tenantId: String,
    @ColumnInfo(name="Name") val name: String,
    @ColumnInfo(name="PrimaryDomain") val primaryDomain: String,
    @ColumnInfo(name="CloudId") val cloudId: Int
)
