package com.example.disciplo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.disciplo.ui.menu.MenuActivity
import com.example.disciplo.ui.splash.SplashActivity
import com.example.disciplo.ui.students.StudentsFormActivity
import com.example.disciplo.ui.students.StudentsListActivity
import com.example.disciplo.ui.subjects.SubjectFormActivity
import com.example.disciplo.ui.subjects.SubjectListActivity

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashActivity(navController)
        }
        // Menu principal
        composable(Routes.MenuActivity.route) {
            MenuActivity(navController)
        }

        // Alunos
        composable(Routes.StudentsListActivity.route) {
            StudentsListActivity(navController)
        }
        composable(Routes.StudentsFormActivity.route) {
            StudentsFormActivity(navController = navController)
        }
        composable(
            route = "${Routes.StudentsFormActivity.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            StudentsFormActivity(navController = navController, studentId = id)
        }

        // Disciplinas
        composable(Routes.SubjectListActivity.route) {
            SubjectListActivity(navController)
        }
        composable(Routes.SubjectFormActivity.route) {
            SubjectFormActivity(navController)
        }

        composable(
            route = "subject_form/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            SubjectFormActivity(navController, subjectName = name)
        }
    }
}
