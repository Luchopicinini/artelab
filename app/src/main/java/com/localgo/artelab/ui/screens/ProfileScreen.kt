package com.localgo.artelab.ui.screens

import android.Manifest // permisos
import android.content.Context // contexto app
import android.net.Uri // fotos y archivos
import android.os.Build // chequeo de version de android
import androidx.activity.compose.rememberLauncherForActivityResult // launcher para actividades con resultado en compose
import androidx.activity.result.contract.ActivityResultContracts // pedir permisos
import androidx.compose.foundation.clickable // hacer views clickeables
import androidx.compose.foundation.layout.* // row, column, box, spacer y modificadores de layout
import androidx.compose.foundation.shape.CircleShape // forma circular para clips y bordes
import androidx.compose.material.icons.Icons // contenedor de iconos material
import androidx.compose.material.icons.filled.CameraAlt // icono de camara
import androidx.compose.material.icons.filled.Person // icono de persona
import androidx.compose.material3.* // componentes material 3 (button, text, etc)
import androidx.compose.runtime.* // estado y efectos de compose (remember, mutableStateOf)
import androidx.compose.ui.Alignment // alineaciones (Center, Start, etc)
import androidx.compose.ui.Modifier // modificador base para compose
import androidx.compose.ui.draw.clip // recortar vista con una forma
import androidx.compose.ui.graphics.Color // colores
import androidx.compose.ui.layout.ContentScale // como ajustar imagenes (Crop, Fit)
import androidx.compose.ui.platform.LocalContext // obtener context dentro de compose
import androidx.compose.ui.unit.dp // unidades de densidad (dp)
import androidx.core.content.FileProvider // compartir archivos con uris seguras
import androidx.lifecycle.viewmodel.compose.viewModel // obtener viiewmodel en compose
import coil.compose.AsyncImage // cargar imagenes asincronas
import com.google.accompanist.permissions.ExperimentalPermissionsApi //  API experimental de permisos
import com.google.accompanist.permissions.rememberMultiplePermissionsState // estado para varios permisos
import com.localgo.artelab.viewmodel.ProfileViewModel // tu ViewModel de perfil
import java.io.File // manejo archivos locales
import java.text.SimpleDateFormat // formateo de fechas
import java.util.* // date, calendar, etc

@OptIn(ExperimentalPermissionsApi::class)//permisos experimentales
@Composable//funcion dibujar ui
fun ProfileScreen(onBackClick: () -> Unit = {}) {// si no pasa nada es una funcion vacia
    val context = LocalContext.current //contexto de android dentro de compose
    val viewModel: ProfileViewModel = viewModel() //obtiene el viewmodel de perfil
    val uiState by viewModel.uiState.collectAsState() //observa el estado y redibuja al cambiar
    var showDialog by remember { mutableStateOf(false) }//estado local para mostrar o ocultar dialogo
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) } // estado local para la URI temporal de la foto de camara

    // Permisos
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES
        )
    } else {
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    val permissionState = rememberMultiplePermissionsState(permissions)// crea y recuerda el estado de multiples permisos para pedir y chequearlos en la ui

    // Launcher camara(tomar foto y guardar en la URI)
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempCameraUri != null) {
            viewModel.updateAvatar(tempCameraUri)
        }
    }

    //  Launcher galería(selecciona imagen de la galeria)
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { viewModel.updateAvatar(it) }
    }

    // Dialogo de seleccion
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Seleccionar imagen") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        showDialog = false
                        if (permissionState.allPermissionsGranted) {
                            tempCameraUri = createImageUri(context)
                            tempCameraUri?.let { takePictureLauncher.launch(it) }
                        } else {
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }) {
                        Text("Tomar foto con cámara")
                    }

                    Button(onClick = {
                        showDialog = false
                        if (permissionState.allPermissionsGranted) {
                            pickImageLauncher.launch("image/*")
                        } else {
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }) {
                        Text("Elegir desde galería")
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // UI principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(150.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            // Mostrar imagen guardada o icono por defecto
            if (uiState.avatarUri != null) {
                AsyncImage(
                    model = uiState.avatarUri,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .clickable { showDialog = true }
                )
            } else {
                Surface(
                    modifier = Modifier
                        .size(150.dp)
                        .clickable { showDialog = true },
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Seleccionar avatar",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp)
                    )
                }
            }

            // Icono de camara pequeño
            Surface(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { showDialog = true },
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Cambiar foto",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(uiState.userName, style = MaterialTheme.typography.titleLarge)
        Text(uiState.userEmail, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBackClick) {
            Text("Volver")
        }
    }
}

/**
 * crea un URI temporal para guardar la foto capturada por la camara
 */
private fun createImageUri(context: Context): Uri? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "profile_avatar_$timeStamp.jpg"
    val storageDir = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)

    return try {
        val imageFile = File(storageDir, imageFileName)
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            imageFile
        )
    } catch (e: Exception) {
        null
    }
}
