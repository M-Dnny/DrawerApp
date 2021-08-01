package com.example.drawerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Screen("Home $Page")
}

@Composable
fun MusicScreen() {
    Screen("Music $Page")
}

@Composable
fun MoviesScreen() {
    Screen("Movies $Page")
}

@Composable
fun BooksScreen() {
    Screen("Books $Page")
}

@Composable
fun ProfileScreen() {
    Screen("Profile $Page")
}

@Composable
fun SettingsScreen() {
    Screen("Setting $Page")

}

var Page: String = "Page"

@Composable
fun Screen(text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
            .wrapContentSize(Alignment.Center)
    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

