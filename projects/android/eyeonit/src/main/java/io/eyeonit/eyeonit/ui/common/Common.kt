package io.eyeonit.eyeonit.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import io.eyeonit.eyeonit.data.EyeonitData
import io.eyeonit.eyeonit.data.Watchable
import io.eyeonit.eyeonit.ui.theme.DarkGray

public val DefaultPadding = 12.dp

@Composable
private fun <T> BaseCard(
    title: String,
    data: List<T>,
    row: @Composable (T) -> Unit
) {
        Column {
            Column(androidx.compose.ui.Modifier.padding(DefaultPadding)) {
                Text(text = title, style = MaterialTheme.typography.subtitle2)
            }
            Column(Modifier.padding(start = 16.dp, top = 4.dp, end = 8.dp)) {
                data.forEach { row(it) }
            }
        }
}

/**
 * The Accounts card within the Rally Overview screen.
 */
@Composable
fun WatchablesCard(
    onWatchableClick: (String) -> Unit = {}
) {
    BaseCard(
        title = "Watchables",
        data = EyeonitData.watchables
    ){
        watchable ->
        WatchableRow(
            modifier = Modifier.clickable {
                onWatchableClick(watchable.name)
            },
            watchable = watchable
        )
    }
}


@Composable
private fun WatchableRow(
    modifier: Modifier = Modifier,
    watchable: Watchable
) {
    Row(
        modifier = modifier
            .height(68.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography
        Column(Modifier) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Icon(
                    imageVector = watchable.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                )
            }
        }
        Column(Modifier) {
            Text(text = watchable.name)
        }
        Spacer(Modifier.weight(1f))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
            )
        }
    }

}
