package io.eyeonit.eyeonit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Waves
import androidx.compose.ui.graphics.vector.ImageVector

data class Watchable(
    val name: String,
    val featureId: Int,
    val icon: ImageVector
)

//https://fonts.google.com/icons
object EyeonitData {
  val watchables: List<Watchable> = listOf(
      Watchable(
          "mailbox",
          1,
          Icons.Outlined.Mail
      ),
      Watchable(
          "calendar",
          2,
          Icons.Outlined.CalendarToday
      ),
      Watchable(
          "bugs",
          3,
          Icons.Outlined.BugReport
      ),
      Watchable(
          "tides",
          4,
          Icons.Outlined.Waves
      )
  )
}


