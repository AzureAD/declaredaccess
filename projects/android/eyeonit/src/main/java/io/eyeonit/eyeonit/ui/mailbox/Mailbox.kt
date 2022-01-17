package io.eyeonit.eyeonit.ui.mailbox

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.eyeonit.eyeonit.ui.common.DefaultPadding
import io.eyeonit.eyeonit.ui.theme.EyeonitTheme

@Composable
fun EmailWatchable(
    title: String
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            Modifier.padding(DefaultPadding),
        ) {
            Text(text = title, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(16.dp))
            var text by remember { mutableStateOf("enter your query here") }
            OutlinedTextField(
                value = text,
                label = {Label("mailbox query")},
                onValueChange = { text = it }
            )
        }
    }
}

@Composable
fun Label(
    title: String
){
    Text(text = title, style = MaterialTheme.typography.subtitle2)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EyeonitTheme {
        EmailWatchable(title = "mailbox query")
    }

}