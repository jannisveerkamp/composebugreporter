package de.jannisveerkamp.composebugreporter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun KeyboardScrollScreen() {
    AppBodyContent(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    )
}

@Composable
fun AppBodyContent(modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier
            .weight(1f)
            .background(Color.Gray))
    }
}
