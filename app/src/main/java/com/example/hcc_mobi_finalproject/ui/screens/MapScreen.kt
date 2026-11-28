package com.example.hcc_mobi_finalproject.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hcc_mobi_finalproject.MainViewModel
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.annotation.Marker

/*
GOAL:
Add code to display a Mapbox map and center/zoom the map on the device's location (somewhere in HRM).
 */



@Composable
fun MapScreen(viewModel: MainViewModel) {

    val location = viewModel.currentLocation
    location?.let {
        MapboxMap(
            Modifier.fillMaxSize(),
            mapViewportState = rememberMapViewportState {
                setCameraOptions {
                    zoom(12.5)
                    center(Point.fromLngLat(location.longitude, location.latitude))
                    pitch(0.0)
                    bearing(0.0)
                }
            }) {
            Marker(
                point = Point.fromLngLat(location.longitude, location.latitude)
            )
        }
    }

    Column() {
        Text("Map Screen")
    }

}
