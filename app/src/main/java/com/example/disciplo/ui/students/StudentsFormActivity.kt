package com.example.disciplo.ui.students

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.disciplo.repository.StudentRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsFormActivity(
    navController: NavController,
    studentId: Int? = null
) {
    val context = LocalContext.current
    val isEditing = studentId != null
    val student = remember { studentId?.let { StudentRepository.getById(it) } }

    var name by remember { mutableStateOf(student?.name ?: "") }
    val allSubjects = remember { SubjectRepository.getAll() }
    var selectedSubjects by remember {
        mutableStateOf(student?.subjects ?: emptyList())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEditing) "Editar Aluno" else "Cadastro de Aluno",
                        color = Color.Black
                    )
                },
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
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Selecione as disciplinas:")
            Spacer(modifier = Modifier.height(8.dp))

            allSubjects.forEach { subject ->
                val isChecked = subject in selectedSubjects
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {
                            selectedSubjects = if (it) {
                                selectedSubjects + subject
                            } else {
                                selectedSubjects - subject
                            }
                        }
                    )
                    Text(text = subject)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        if (isEditing && studentId != null) {
                            StudentRepository.updateStudent(studentId, name, selectedSubjects)
                            Toast.makeText(context, "Aluno editado com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            StudentRepository.addStudent(name, selectedSubjects)
                            Toast.makeText(context, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Preencha o nome do aluno", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Salvar")
            }
        }
    }
}
