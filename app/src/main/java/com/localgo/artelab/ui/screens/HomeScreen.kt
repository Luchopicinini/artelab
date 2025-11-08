package com.localgo.artelab.ui.screens

import androidx.compose.foundation.layout.*//column spacer, padding, size
import androidx.compose.material3.*//text y button
import androidx.compose.runtime.Composable // anotacion para funciones que dibujan UI en compose
import androidx.compose.ui.Modifier //size, padding, etc
import androidx.compose.ui.unit.dp //unidad independiente
import androidx.compose.ui.Alignment //center

@Composable
fun HomeScreen(onProfileClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,         // centra verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // centra horizontalmente
    ) {
        Text(
            text = "Bienvenido a Artelab SPA",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onProfileClick) {
            Text("Ir a perfil")
        }
    }
}

