package de.jannisveerkamp.composebugreporter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import de.jannisveerkamp.composebugreporter.ui.KeyboardScrollScreen
import de.jannisveerkamp.composebugreporter.ui.MainScreen
import de.jannisveerkamp.composebugreporter.ui.theme.ComposeBugreporterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeBugreporterTheme {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        MainScreen()
                        //KeyboardScrollScreen()
                    }
                }
            }
        }
    }
}
