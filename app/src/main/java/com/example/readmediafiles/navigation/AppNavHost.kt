package com.example.readmediafiles.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.readmediafiles.MainViewModel
import com.example.readmediafiles.screens.DetailScreen
import com.example.readmediafiles.screens.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: MainViewModel
){
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ){
        composable( route = AppDestinations.HOME )
        {
            HomeScreen(
                modifier = modifier,
                navigateToDetail = { imageUri ->
                    viewModel.selectUri(imageUri)
                    Log.e("TAG", "AppNavHost: ${viewModel
                        .uri.value}", )
                    navigateTo(
                        destination = AppDestinations.DETAIL,
                        navController = navController
                    )
                },
                viewModel = viewModel
            )
        }

        composable( route = AppDestinations.DETAIL ) {
            DetailScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }
    }
}

fun navigateTo(destination: String, navController: NavHostController){
    navController.navigate(destination){
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}