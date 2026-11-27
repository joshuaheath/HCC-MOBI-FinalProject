/*
MainViewModel.kt
OWNER: Jason
Add code to retrieve and store the device location.

HINT: Check out either my repo in the discord in the 'resources' channel or your own Weather app, dont re-invent the wheel
feel free to ask me questions if you get lost, you got this -Josh
*/
package com.example.hcc_mobi_finalproject

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    var currentLocation by mutableStateOf<Location?>(null)
        private set

    fun fetchLocation() {
        val context = getApplication<Application>()

        val hasFine = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (!hasFine && !hasCoarse) return

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                currentLocation = location
            }
    }
}