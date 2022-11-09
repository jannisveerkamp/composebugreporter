package de.jannisveerkamp.composebugreporter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import de.jannisveerkamp.composebugreporter.ui.theme.ComposeBugreporterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val hugeText = "abcd-efgh-".repeat(50_000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeBugreporterTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(top = 24.dp, bottom = 48.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(text = "${hugeText.length}")
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        Text(
                            text = hugeText,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }
}
