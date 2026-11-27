package com.example.hcc_mobi_finalproject.ui

/*

    Mostly stolen from my weather app with some changes.

    Displays the app's top bar and bottom navigation bar, with 1 navigation item
    that goes to the home screen.

    Location is requested via Jasons "LocationPermission.kt"

 */

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hcc_mobi_finalproject.R
import com.example.hcc_mobi_finalproject.ui.theme.HCCMOBIFinalProjectTheme

/*

    All the screens the bottom bar can go to.
    Only the map for now, add more here later.

 */

enum class Screen(val label: String, @DrawableRes val icon: Int) {
    Map("Map", R.drawable.ic_map),
}

/*

    Main app layout. Top bar, whatever screen is picked, then the bottom bar.

 */

@Composable
fun AppNavigation() {
    // remembers what screen your on, even if the phone gets rotated
    var currentScreen by rememberSaveable { mutableStateOf(Screen.Map) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar() },
        bottomBar = {
            AppBottomBar(
                currentScreen = currentScreen,
                onScreenSelected = { currentScreen = it }
            )
        }
    ) { innerPadding ->
        // innerPadding stops the screen from going under the bars
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                // TODO: swap for Kieras MapScreen() once her map is done
                Screen.Map -> MapScreenPlaceholder()
            }
        }
    }
}

/*

    Top bar, just shows the app name.
    Pulls the name from strings.xml so it changes with Kamalis branding.

 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

/*

    Bottom bar. Makes a button for every screen in the Screen list.

 */
@Composable
fun AppBottomBar(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar {
        Screen.entries.forEach { screen ->
            NavigationBarItem(
                selected = screen == currentScreen,
                onClick = { onScreenSelected(screen) },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.label
                    )
                },
                label = { Text(text = screen.label) }
            )
        }
    }
}

/*

    Placeholder until Kieras Mapbox map is ready.

 */
@Composable
private fun MapScreenPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Map goes here")
    }
}

/*

    Preview so you can see the bars in Android Studio without running the app.

 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    HCCMOBIFinalProjectTheme {
        AppNavigation()
    }
}

