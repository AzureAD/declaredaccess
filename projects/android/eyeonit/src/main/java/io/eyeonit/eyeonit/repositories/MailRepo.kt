package io.eyeonit.eyeonit.repositories

/**
 * Let's not get back the entire message from graph.... just the fields we want
 */
data class Mail(
    val subject: String,
    val webLink: String,
    val importance: String,
    val receivedDateTime: String
)



/** Access MS Graph
 * https://docs.microsoft.com/en-us/graph/search-query-parameter
 */
object MailRepo {

    /**
     * Returns unread mail matching configured query
     */
    fun getUnreadMail(query:String): Any {
        return "";
    }

}