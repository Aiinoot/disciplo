package com.example.disciplo.ui.students

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.disciplo.models.Student
import com.example.disciplo.repository.StudentRepository
import com.example.disciplo.navigation.Routes
import com.example.disciplo.ui.components.CustomDeleteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsListActivity(navController: NavController) {
    var students by remember { mutableStateOf(StudentRepository.getAll()) }
    var studentToDelete by remember { mutableStateOf<Student?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Alunos", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            students.forEach { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(student.name, style = MaterialTheme.typography.bodyLarge)

                        Row {
                            IconButton(onClick = {
                                navController.navigate("${Routes.StudentsFormActivity.route}/${student.id}")
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar",
                                    tint = Color.Black
                                )
                            }

                            IconButton(
                                onClick = { studentToDelete = student },
                                modifier = Modifier
                                    .background(Color(0xFFFFEBEE), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Excluir",
                                    tint = Color(0xFFD32F2F)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Routes.StudentsFormActivity.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Cadastrar", color = Color.Black)
            }
        }

        studentToDelete?.let { student ->
            CustomDeleteDialog(
                title = "Excluir Aluno",
                message = "Deseja realmente excluir ${student.name}?",
                onConfirm = {
                    StudentRepository.removeStudent(student.id)
                    students = StudentRepository.getAll()
                    studentToDelete = null
                },
                onDismiss = { studentToDelete = null }
            )
        }
    }
}
