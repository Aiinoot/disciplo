package com.example.disciplo.ui.subjects

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectFormActivity(
    navController: NavController,
    subjectName: String? = null
) {
    val context = LocalContext.current
    val isEditing = subjectName != null
    var name by remember { mutableStateOf(subjectName ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEditing) "Editar Disciplina" else "Cadastrar Disciplina",
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
                label = { Text("Nome da disciplina") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        if (isEditing && subjectName != null) {
                            SubjectRepository.update(subjectName, name)
                            Toast.makeText(context, "Disciplina atualizada", Toast.LENGTH_SHORT).show()
                        } else {
                            SubjectRepository.add(name)
                            Toast.makeText(context, "Disciplina cadastrada", Toast.LENGTH_SHORT).show()
                        }

                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Preencha o nome", Toast.LENGTH_SHORT).show()
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
