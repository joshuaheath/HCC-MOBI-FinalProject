package com.example.hcc_mobi_finalproject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hcc_mobi_finalproject.ui.theme.HCCMOBIFinalProjectTheme
import com.example.hcc_mobi_finalproject.ui.AppNavigation
import com.example.hcc_mobi_finalproject.ui.RequestLocationPermission

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