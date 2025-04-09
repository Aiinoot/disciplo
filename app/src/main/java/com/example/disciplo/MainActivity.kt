package com.example.disciplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.disciplo.navigation.AppNavigation
import com.example.disciplo.theme.DisciploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisciploTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
