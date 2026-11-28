package com.example.hcc_mobi_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hcc_mobi_finalproject.componets.AppNavigation
import com.example.hcc_mobi_finalproject.componets.RequestLocationPermission
import androidx.activity.viewModels
import com.example.hcc_mobi_finalproject.ui.theme.HCCMOBIFinalProjectTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HCCMOBIFinalProjectTheme {
                AppNavigation()
                RequestLocationPermission { granted ->
                    if (granted) {
                        viewModel.fetchLocation()
                        println("=========================== CURRENT LOCATION ===========================")
                        println(viewModel.currentLocation) // TODO, This returns as null via the debug
                    }
                }
            }
        }
    }
}

