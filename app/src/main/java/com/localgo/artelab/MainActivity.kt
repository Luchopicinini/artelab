package com.localgo.artelab

import android.os.Bundle //para guardar/restaurar estado y pasar datos entre pantallas
import androidx.activity.ComponentActivity// facilita la creacion de la app
import androidx.activity.compose.setContent// entrada principal para montar la ui de jetpack compose
import com.localgo.artelab.ui.navigation.AppNavigation// importa el app navegation de artelab
import com.localgo.artelab.ui.theme.ArteLabTheme// importa el theme de artelab

class MainActivity : ComponentActivity() {// el component activity da soporte a Lifecycle, ViewModel, SavedState y APIs modernas (ideal para Compose)
    override fun onCreate(savedInstanceState: Bundle?) { //configura todo y deja lista la pantalla para mostrarse
        super.onCreate(savedInstanceState)//llama la inicializacion basica de la Activity para que android la prepare bien antes de tu propio codigo.
        setContent { //dibuja la pantalla con el estilo de ArteLab y muestra la pantalla que corresponda segun la navegacion de la app
            ArteLabTheme {
                AppNavigation()
            }
        }
    }
}
