package com.localgo.artelab.ui.components

import androidx.compose.foundation.layout.*//Row, Column, Spacer, padding, size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt //icono de camara
import androidx.compose.material.icons.filled.PhotoLibrary //icono foto libreria
import androidx.compose.material3.* //text, button, dialog
import androidx.compose.runtime.Composable //funciones ui
import androidx.compose.ui.Alignment //center
import androidx.compose.ui.Modifier //tamaño, padding, clic,
import androidx.compose.ui.graphics.vector.ImageVector//imagenes vectoriales
import androidx.compose.ui.text.font.FontWeight//fuente de texto
import androidx.compose.ui.unit.dp
import com.localgo.artelab.ui.theme.*

@Composable
fun ImagePickerDialog(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,//tocar camara
    onGalleryClick: () -> Unit//tocar galeria
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {}, // Requerido aunque esté vacío
        dismissButton = {
            // boton cancelar
            OutlinedButton(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        },
        title = {//texto seleccionar imagen
            Text(
                text = "Seleccionar imagen",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Foreground
                )
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Elige cómo deseas seleccionar tu imagen:",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ForegroundMuted
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))//espaciador vertical 8 dp

                // opcion de camara
                ImagePickerOption(
                    icon = Icons.Filled.CameraAlt,
                    title = "Tomar foto",
                    description = "Abre la cámara para capturar una nueva foto",
                    onClick = onCameraClick
                )

                Divider() //  linea fina para dividir contenidos

                // opcion de galeria
                ImagePickerOption(
                    icon = Icons.Filled.PhotoLibrary,
                    title = "Elegir de galería",
                    description = "Selecciona una imagen de tu dispositivo",
                    onClick = onGalleryClick
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    )
}

@Composable
private fun ImagePickerOption(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    //  reemplazo de shadcnCard con card nativo de material3
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Primary,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Foreground
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = ForegroundMuted
                    )
                )
            }
        }
    }
}
