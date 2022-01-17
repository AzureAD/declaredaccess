package io.swath.microsoft.security.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION

@Entity(tableName = "AccountFeatureEnabled", primaryKeys = ["AccountId", "FeatureId", "OrganizationId"], foreignKeys = [
    ForeignKey(
        entity = Account::class,
        parentColumns = ["Id"],
        childColumns = ["AccountId"],
        onDelete = NO_ACTION
    ),
    ForeignKey(
        entity = Feature::class,
        parentColumns = ["Id"],
        childColumns = ["FeatureId"],
        onDelete = NO_ACTION
    ),
    ForeignKey(
        entity = Organization::class,
        parentColumns = ["TenantId"],
        childColumns = ["OrganizationId"],
        onDelete = NO_ACTION
    )
])
data class AccountFeature(
    @ColumnInfo(name="AccountId") val accountId: Int,
    @ColumnInfo(name = "FeatureId") val featureId: Int,
    @ColumnInfo(name = "OrganizationId") val organizationId: String,

    @ColumnInfo(name="Enabled") val enabled: Int,
    @ColumnInfo(name="EnabledDate") val enabledDate:Int,

)
