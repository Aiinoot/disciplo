package com.example.disciplo.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.disciplo.navigation.Routes
import kotlinx.coroutines.delay
import com.example.disciplo.R

@Composable
fun SplashActivity(navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Routes.MenuActivity.route) {
            popUpTo(0) // limpa o backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_disciplo),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp)
        )
    }
}
