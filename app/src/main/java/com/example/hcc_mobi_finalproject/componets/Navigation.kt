package com.example.hcc_mobi_finalproject.componets

/*

    Mostly stolen from my weather app with some changes.

    Displays the app's top bar and bottom navigation bar, with 1 navigation item
    that goes to the home screen.

    Location is requested via "LocationPermission.kt"

 */

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hcc_mobi_finalproject.MainViewModel
import com.example.hcc_mobi_finalproject.R
import com.example.hcc_mobi_finalproject.ui.screens.MapScreen
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
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation() {
    // remembers what screen your on, even if the phone gets rotated
    var currentScreen by rememberSaveable { mutableStateOf(Screen.Map) }

    val viewModel: MainViewModel = viewModel()

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
                Screen.Map -> MapScreen(viewModel)
            }
        }
    }
}

/*

    Top bar, just shows the app name in bold.
    Pulls the name from strings.xml so it changes with the app branding.

 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

/*

    Bottom bar with rounded top corners.
    Makes a button for every screen in the Screen list.

 */
@Composable
fun AppBottomBar(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
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

    Placeholder until the Mapbox map is ready.
    Big faded map icon with some text under it.

 */
@Composable
private fun MapScreenPlaceholder() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_map),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
        Text(
            text = "Map coming soon",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
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

//Kamali was here,
//changed fontStyle to fontFamily and made the font serif
//changed font weight to semibold.