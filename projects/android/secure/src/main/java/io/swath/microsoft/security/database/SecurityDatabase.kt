package io.swath.microsoft.security.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.swath.microsoft.security.database.dao.*
import io.swath.microsoft.security.database.entities.*

//TODO: Exporting the schema and having a plan for versioning/migration
@Database(entities = [Account::class, Application::class, Cloud::class, Feature::class, Organization::class, AccountFeature::class], version = 1, exportSchema = false)
abstract class SecurityDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun organizationDao(): OrganizationDao
    abstract fun featureDao(): FeatureDao
    abstract fun cloudDao(): CloudDao
    abstract fun applicationDao(): ApplicationDao
}