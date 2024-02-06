package com.deltax72.onlineshop.navigation

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deltax72.onlineshop.di.viewModelsModule
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.navigation.states.NavigationViewState
import com.deltax72.onlineshop.navigation.views.BottomNavigationBarView
import com.deltax72.onlineshop.screens.Screen
import com.deltax72.onlineshop.screens.account.AccountScreen
import com.deltax72.onlineshop.screens.catalog.CatalogScreen
import com.deltax72.onlineshop.screens.details.DetailsScreen
import com.deltax72.onlineshop.screens.favourites.FavouritesScreen
import com.deltax72.onlineshop.screens.registration.RegistrationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navigationViewModel: NavigationViewModel,
) {
    val rootNavController = rememberNavController()

    val state by navigationViewModel.state.collectAsState()

//    val navigator by navigationViewModel.navigator.collectAsState()
    val bottomState by navigationViewModel.bottomState.collectAsState()
//    val topState by navigationViewModel.topState.collectAsState()

    val activity = (LocalContext.current) as Activity

    Scaffold(
        topBar = {
//            TopNavigationBarView(
//                state = topState,
//            )
        },
        bottomBar = {
            BottomNavigationBarView(
                state = bottomState,
            )
        }
    ) { paddingValues ->
        when (state) {
            is NavigationViewState.Closed -> {

            }
            is NavigationViewState.Display -> {
                val sharedViewModel = viewModelsModule.details

                NavHost(
                    navController = rootNavController,
                    startDestination = Screen.Registration.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(
                        route = Screen.Registration.route
                    ) {
                        val viewModel = viewModelsModule.registration

                        RegistrationScreen(
                            navigationViewModel = navigationViewModel,
                            viewModel = viewModel
                        )
                    }
                    composable(
                        route = Screen.Catalog.route
                    ) {
                        val viewModel = viewModelsModule.catalog

                        CatalogScreen(
                            navigationViewModel = navigationViewModel,
                            viewModel = viewModel,
                            sharedViewModel = sharedViewModel
                        )
                    }

                    composable(
                        route = Screen.Details.route
                    ) {
                        DetailsScreen(
                            navigationViewModel = navigationViewModel,
                            viewModel = sharedViewModel,
                        )
                    }

                    composable(
                        route = Screen.Account.route
                    ) {
                        val viewModel = viewModelsModule.account

                        AccountScreen(
                            navigationViewModel = navigationViewModel,
                            viewModel = viewModel
                        )
                    }

                    composable(
                        route = Screen.Favourites.route
                    ) {
                        val viewModel = viewModelsModule.favourites

                        FavouritesScreen(
                            navigationViewModel = navigationViewModel,
                            viewModel = viewModel,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
            is NavigationViewState.Exit -> {
                activity.finish()
            }
        }
    }

    LaunchedEffect(Unit) {
        navigationViewModel.onEvent(
            NavigationEvent.EnterNavigation(
                rootNavController = rootNavController
            )
        )
    }
}