package io.eyeonit.eyeonit.ui.watchables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import io.eyeonit.eyeonit.ui.common.WatchablesCard

@Composable
fun WatchablesBody(
    onWatchableClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
    ) {
        WatchablesCard(onWatchableClick)
    }
}