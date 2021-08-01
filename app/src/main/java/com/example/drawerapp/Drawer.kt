package com.example.drawerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val items = listOf(
    NavDrawerItems.Home,
    NavDrawerItems.Music,
    NavDrawerItems.Movies,
    NavDrawerItems.Books,
    NavDrawerItems.Profile,
    NavDrawerItems.Setting,
)

@Composable
fun Drawer() {
    ScaffoldDrawer(
        scope = rememberCoroutineScope(),
        scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed)),
        navController = rememberNavController()
    )
}

@Composable
fun ScaffoldDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(scaffoldState = scaffoldState, scope = scope) },
        drawerContent = {
            MyDrawer(
                scaffoldState = scaffoldState,
                navController = navController,
                scope = scope
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 400.dp)
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun TopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed)
                            drawerState.open() else drawerState.close()
                    }
                },
                content = {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            )
        },
        title = {
            Text(
                text = "Drawer",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
    )
}

@Composable
fun MyDrawer(scaffoldState: ScaffoldState, navController: NavController, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Text(
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.height(1.dp)
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(
                item = item,
                selected = currentRoute == item.route,
                onItemClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true

//                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController as NavHostController,
        startDestination = NavDrawerItems.Home.route,
    ) {
        composable(NavDrawerItems.Home.route) {
            HomeScreen()
        }
        composable(NavDrawerItems.Music.route) {
            MusicScreen()
        }
        composable(NavDrawerItems.Movies.route) {
            MoviesScreen()
        }
        composable(NavDrawerItems.Books.route) {
            BooksScreen()
        }
        composable(NavDrawerItems.Profile.route) {
            ProfileScreen()
        }
        composable(NavDrawerItems.Setting.route) {
            SettingsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Drawer()
}