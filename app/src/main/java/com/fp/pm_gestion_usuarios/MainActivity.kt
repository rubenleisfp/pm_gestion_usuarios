package com.fp.pm_gestion_usuarios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fp.pm_gestion_usuarios.ui.theme.Pm_gestion_usuariosTheme
import com.fp.pm_gestion_usuarios.vm.GestionUsuarioViewModel
import com.fp.pm_gestion_usuarios.vm.state.GestionUsuarioState
import com.fp.pm_gestion_usuarios.vm.state.Usuario

class MainActivity : ComponentActivity() {

    private val gestionUsuarioViewModel by viewModels<GestionUsuarioViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pm_gestion_usuariosTheme {
                UsuarioApp(gestionUsuarioViewModel)
            }
        }
    }
}

@Composable
fun UsuarioApp(gestionUsuarioViewModel: GestionUsuarioViewModel = viewModel()) {
    val uiState by gestionUsuarioViewModel.uiState.collectAsState()

    UsuarioScreen(
        uiState = uiState,
        onNombreChanged = { gestionUsuarioViewModel.onNombreChanged(it) },
        onAgregarUsuario = { gestionUsuarioViewModel.agregarUsuario() },
        onCambiarEstado = { id -> gestionUsuarioViewModel.cambiarEstadoUsuario(id) }
    )
}

@Composable
fun UsuarioScreen(
    uiState: GestionUsuarioState,
    onNombreChanged: (String) -> Unit,
    onAgregarUsuario: () -> Unit,
    onCambiarEstado: (Int) -> Unit
) {
   Text("A implementar por el alumno")
}

@Preview(showBackground = true)
@Composable
fun UsuarioScreenPreview() {

    val usuariosFake = listOf(
        Usuario(id = 1, nombre = "Ana", activo = true),
        Usuario(id = 2, nombre = "Pedro", activo = false)
    )
    val uiState = GestionUsuarioState(usuarios = usuariosFake)


    UsuarioScreen(
        uiState = uiState,
        onNombreChanged = {},
        onAgregarUsuario = {},
        onCambiarEstado = {}
    )
}

