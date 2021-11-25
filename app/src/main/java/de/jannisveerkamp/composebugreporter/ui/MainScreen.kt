package de.jannisveerkamp.composebugreporter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.lifecycle.HiltViewModel
import de.jannisveerkamp.composebugreporter.ui.theme.ComposeBugreporterTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start",
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        composable("start") { navBackStackEntry ->
            val viewModel = viewModel(
                FirstScreenViewModel::class.java,
                factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            )
            val text = viewModel.data.collectAsState()

            Content(
                text = text.value,
                buttonText = "Open Dialog",
                onClick = {
                    navController.navigate("dialog")
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // replace this with composable("dialog") won't let the app crash
        dialog("dialog") { navBackStackEntry ->
            val viewModel = viewModel(
                DialogViewModel::class.java,
                factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            )
            val text = viewModel.data.collectAsState()

            Content(
                modifier = Modifier.padding(16.dp),
                text = text.value,
                buttonText = "Close Dialog",
                onClick = {
                    viewModel.data.tryEmit("123")
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun Content(text: String, buttonText: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    var enterTextVeryFastToMakeMeCrash by remember { mutableStateOf("") }
    Column(
        modifier = modifier.background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$text screen")
        Button(onClick = onClick) {
            Text(buttonText)
        }
        TextField(
            value = enterTextVeryFastToMakeMeCrash,
            onValueChange = { enterTextVeryFastToMakeMeCrash = it },
            modifier = Modifier.height(128.dp)
        )
        TextField(
            value = enterTextVeryFastToMakeMeCrash,
            readOnly = true,
            onValueChange = {  },
            modifier = Modifier.height(128.dp)
        )
    }
}

@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel() {
    val data: StateFlow<String> = MutableStateFlow("first")
}

@HiltViewModel
class DialogViewModel @Inject constructor() : ViewModel() {
    val data: MutableStateFlow<String> = MutableStateFlow("dialog")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBugreporterTheme {
        MainScreen()
    }
}