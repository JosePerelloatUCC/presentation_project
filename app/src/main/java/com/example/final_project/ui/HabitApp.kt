package com.example.final_project.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.Screens.ChooseHabits
import com.example.finalproject.ui.Screens.ListofHabits

enum class HabitAppScreens() {
    MAIN_SCREEN,
    CHOOSE_HABITS,
    CHECK_PROGRESS
}


@Composable
fun HabitApp(
    modifier: Modifier = Modifier,
    ProjectViewModel: ProjectViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by ProjectViewModel.uiState.collectAsState()
    ProjectViewModel.loadHabitList(context = LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = HabitAppScreens.MAIN_SCREEN.name ,)
    {
        composable(route = HabitAppScreens.MAIN_SCREEN.name) {
            ListofHabits(
                habits = ProjectViewModel.habits,
                onCreatenewHabitClick = {
                    navController.navigate(HabitAppScreens.CHOOSE_HABITS.name) },
            )
        }
        composable(route = HabitAppScreens.CHOOSE_HABITS.name) {
            ChooseHabits(
                uiState.habitDescription,
                uiState.timeofNotification,
                uiState.userNotification,
                onSaveClick = {
                        context, habitDescription, notificaitonTime, isNotified ->
                    ProjectViewModel.saveHabitfromUser(context, habitDescription, notificaitonTime, isNotified)
                }
            )
        }
    }
}
