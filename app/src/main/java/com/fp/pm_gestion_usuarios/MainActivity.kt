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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fp.pm_gestion_usuarios.ui.theme.Pm_gestion_usuariosTheme
import com.fp.pm_gestion_usuarios.vm.UsuarioViewModel
import com.fp.pm_gestion_usuarios.vm.state.UsuarioState

class MainActivity : ComponentActivity() {

    private val usuarioViewModel by viewModels<UsuarioViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pm_gestion_usuariosTheme {
                UsuarioApp(usuarioViewModel)
            }
        }
    }
}

@Composable
fun UsuarioApp(usuarioViewModel: UsuarioViewModel = viewModel()) {
    val nombre by usuarioViewModel.nombre.collectAsState()
    val usuarios by usuarioViewModel.usuarios.collectAsState()

    UsuarioScreen(
        nombre = nombre,
        usuarios = usuarios,
        onNombreChanged = { usuarioViewModel.onNombreChanged(it) },
        onAgregarUsuario = { usuarioViewModel.agregarUsuario() },
        onCambiarEstado = { id -> usuarioViewModel.cambiarEstadoUsuario(id) }
    )
}

@Composable
fun UsuarioScreen(
    nombre: String,
    usuarios: List<UsuarioState>,
    onNombreChanged: (String) -> Unit,
    onAgregarUsuario: () -> Unit,
    onCambiarEstado: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Gestión de Usuarios", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = nombre,
            onValueChange = { onNombreChanged(it) },
            label = { Text("Nombre del usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onAgregarUsuario,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Lista de usuarios:")

        LazyColumn {
            items(usuarios) { usuario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${usuario.nombre} (ID: ${usuario.id}) - ${if (usuario.activo) "Activo" else "Inactivo"}")
                    Button(onClick = { onCambiarEstado(usuario.id) }) {
                        Text("Cambiar estado")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsuarioScreenPreview() {
    val usuariosFake = listOf(
        UsuarioState(id = 1, nombre = "Ana", activo = true),
        UsuarioState(id = 2, nombre = "Pedro", activo = false)
    )

    UsuarioScreen(
        nombre = "Nuevo usuario",
        usuarios = usuariosFake,
        onNombreChanged = {},
        onAgregarUsuario = {},
        onCambiarEstado = {}
    )
}

