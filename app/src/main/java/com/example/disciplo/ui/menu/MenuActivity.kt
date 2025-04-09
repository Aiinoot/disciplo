package com.example.disciplo.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.disciplo.navigation.Routes
import com.example.disciplo.repository.StudentRepository
import com.example.disciplo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuActivity(navController: NavController) {
    val alunoCount = StudentRepository.getAll().size
    val disciplinaCount = SubjectRepository.getAll().size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_disciplo),
                contentDescription = "Logo Disciplo",
                modifier = Modifier.height(100.dp)
            )

        }
        Image(
            painter = painterResource(id = R.drawable.menu_ilustracao),
            contentDescription = "Ilustração do Menu",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bem-vindo ao Disciplo",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Escolha como deseja continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate(Routes.StudentsListActivity.route) },
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .width(160.dp)
                    .height(100.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Alunos",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Alunos", color = Color.Black)
                    Text("${alunoCount}", fontSize = 12.sp, color = Color.DarkGray)
                }
            }

            Button(
                onClick = { navController.navigate(Routes.SubjectListActivity.route) },
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .width(160.dp)
                    .height(100.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "Disciplinas",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Disciplinas", color = Color.Black)
                    Text("${disciplinaCount}", fontSize = 12.sp, color = Color.DarkGray)
                }
            }
        }
    }
}
