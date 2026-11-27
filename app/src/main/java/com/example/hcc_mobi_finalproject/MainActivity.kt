package com.example.hcc_mobi_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hcc_mobi_finalproject.componets.AppNavigation
import com.example.hcc_mobi_finalproject.componets.RequestLocationPermission
import com.example.hcc_mobi_finalproject.ui.theme.HCCMOBIFinalProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HCCMOBIFinalProjectTheme {
                RequestLocationPermission()
                AppNavigation()
            }
        }
    }
}

// Josh added Token, yay