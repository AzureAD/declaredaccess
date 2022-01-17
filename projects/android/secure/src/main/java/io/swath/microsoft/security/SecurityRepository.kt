package io.swath.microsoft.security

import android.content.Context
import androidx.room.Room
import io.swath.microsoft.security.database.SecurityDatabase
import io.swath.microsoft.security.database.entities.Account
import io.swath.microsoft.security.database.entities.AccountFeature


/**
 * io.swath.microsoft.security.SecurityRepository.initialize(this.applicationContext)
 */
object SecurityRepository {

    private lateinit var securityDatabase: SecurityDatabase
    private lateinit var currentAccount: Account

    fun initialize(appContext:Context){
        //Load the database
            securityDatabase =
                Room.databaseBuilder(appContext, SecurityDatabase::class.java, "AppSecurity.db")
                    .createFromAsset("database/security.db")
                    .build()

        // Get the accounts if any fixes are needed to identity before proceeding in further use of the app
    }

    /**
     * Name: addAccount
     * Description:
     * - Get List of features:
     *   - that do not require admin consented permissions
     *   - that are intended to be requested by default when adding an account
     *
     */
    fun addAccount(){




    }

    /**
     * Name: getAccounts()
     * Description: Return a list of AccountFeature
     */
    fun getAccountFeatures(): List<AccountFeature>?{

        return null

    }
}