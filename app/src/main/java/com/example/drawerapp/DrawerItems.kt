package com.example.drawerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class NavDrawerItems(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavDrawerItems("home", Icons.Rounded.Home, "Home")
    object Music : NavDrawerItems("music", Icons.Rounded.MusicNote, "Music")
    object Movies : NavDrawerItems("movies", Icons.Rounded.Movie, "Movies")
    object Books : NavDrawerItems("books", Icons.Rounded.Book, "Books")
    object Profile : NavDrawerItems("profile", Icons.Rounded.Person, "Profile")
    object Setting : NavDrawerItems("setting", Icons.Rounded.Settings, "Setting")
}

@Composable
fun DrawerItem(
    item: NavDrawerItems, selected: Boolean, onItemClick: (NavDrawerItems) -> Unit
) {
    val background = if (selected) colorResource(id = R.color.teal_700) else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(background)
            .padding(start = 10.dp), content = {
            TextButton(
                onClick = { onItemClick(item) },
            )
            {
                Image(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = item.title,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    )
}

@Preview
@Composable
fun DrawerPreview() {
    DrawerItem(item = NavDrawerItems.Home, selected = false, onItemClick = {})
}
