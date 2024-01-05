package com.example.consumeapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.consumeapi.ui.home.screen.DestinasiHome
import com.example.consumeapi.ui.home.screen.HomeScreen
import com.example.consumeapi.ui.Kontakk.screen.DestinasiEntry
import com.example.consumeapi.ui.Kontakk.screen.DetailsDestination
import com.example.consumeapi.ui.Kontakk.screen.DetailsScreen
import com.example.consumeapi.ui.Kontakk.screen.EditDestination
import com.example.consumeapi.ui.Kontakk.screen.EntryKontakScreen
import com.example.consumeapi.ui.Kontakk.screen.ItemEditScreen


@Composable
fun PengelolaHalaman(navController : NavHostController = rememberNavController()) {

NavHost(navController = navController,
    startDestination =DestinasiHome.route,
    modifier =Modifier,
){
    composable(DestinasiHome.route) {
        HomeScreen(navigateToItemEntry = {
            navController.navigate(DestinasiEntry.route)
        },
            onDetailClick = {itemId ->
                navController.navigate("${DetailsDestination.route}/$itemId")
                println(itemId)

            })
    }
        composable(DestinasiEntry.route){
            EntryKontakScreen(navigateBack = {
                navController.navigate(
                    DestinasiHome.route
                ) {
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
    composable(
        DetailsDestination.routeWhithArgs,
        arguments = listOf(navArgument(DetailsDestination.kontakId){
            type = NavType.IntType
        })
    ){backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt(DetailsDestination.kontakId)
        itemId?.let {
            DetailsScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                onEditClick = {itemId ->
                    navController.navigate("${EditDestination.route}/$itemId")
                    println(itemId)
                }
            )
        }
    }
    composable(
        EditDestination.routeWithArgs,
        arguments = listOf(navArgument(EditDestination.kontakId){
            type = NavType.IntType
        })
    ){
        ItemEditScreen(navigateBack = {navController.popBackStack()},
            onNavigateUp = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
             })
        }

}

}


