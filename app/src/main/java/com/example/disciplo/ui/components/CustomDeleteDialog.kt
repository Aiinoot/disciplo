package com.example.disciplo.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomDeleteDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Alerta",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(64.dp)
            )
        },
        title = { Text(title, color = Color.Black) },
        text = { Text(message, color = Color.Black) },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEEEEE),
                    contentColor = Color(0xFFFFC107)
                ),
                elevation = ButtonDefaults.buttonElevation(6.dp)
            ) {
                Text("Sim")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107),
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(6.dp)
            ) {
                Text("Cancelar")
            }
        }
    )
}
