package com.example.todolist.navigation

import ProfileHeaderComponent
import com.example.todolist.ui.components.WelcomeMessageComponent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist.ui.components.CustomBottomBar
import com.example.todolist.ui.feature.addedit.AddEditScreen
import com.example.todolist.ui.feature.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()

    var selectedScreen by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                selectedScreen = selectedScreen,
                onScreenSelected = { selectedScreen = it }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ListRoute,
            modifier = Modifier.padding(innerPadding) // Adicionando o padding do Scaffold
        ) {
            composable<ListRoute> {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Componente de boas-vindas
                    ProfileHeaderComponent()

                    Spacer(modifier = Modifier.height(16.dp))

                    WelcomeMessageComponent()

                    Spacer(modifier = Modifier.height(16.dp))

                    // Chama a tela principal de listagem de tarefas
                    ListScreen(
                        navigateToAddEditScreen = { id ->
                            navController.navigate(AddEditRoute(id = id))
                        }
                    )
                }
            }

            composable<AddEditRoute> { backStackEntry ->
                val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
                AddEditScreen(
                    id = addEditRoute.id,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
