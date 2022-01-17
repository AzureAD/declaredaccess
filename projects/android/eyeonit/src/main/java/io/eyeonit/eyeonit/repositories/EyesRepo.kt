package io.eyeonit.eyeonit.repositories

import androidx.compose.ui.graphics.vector.ImageVector
import io.eyeonit.eyeonit.data.Watchable
import kotlinx.serialization.Serializable


/**
 * Let's store the resource tenant as well... until we have a correct resource identifier
 * The combination of Watchable Type, Query and Account Id will auto derive that in the short term
 */
@Serializable
data class Eye(
    val watchableName: String,
    val name: String,
    val query: String,
    val accountId: String,
    val resourceTenantId: String
)

/**
 * Going to just store this in memory for now....
 * Will serialize to JSON Later or to Protobufs...
 *
 * https://kotlinlang.org/docs/serialization.html
 * https://developer.android.com/topic/libraries/architecture/datastore#proto-datastore
 */

object EyesRepo {

    val eyes: MutableList<Eye> = ArrayList()

    fun addEye(eye: Eye){
        eyes.add(eye);
    }

}