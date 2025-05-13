package com.fp.pm_gestion_usuarios.vm

import androidx.lifecycle.ViewModel
import com.fp.pm_gestion_usuarios.vm.state.GestionUsuarioState
import com.fp.pm_gestion_usuarios.vm.state.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GestionUsuarioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GestionUsuarioState())
    val uiState: StateFlow<GestionUsuarioState> get() = _uiState

    private val _proximoId = MutableStateFlow(1)

    fun onNombreChanged(nuevoNombre: String) {
        _uiState.value = _uiState.value.copy(nombre = nuevoNombre)
    }

    fun agregarUsuario() {
        val nuevoUsuario = Usuario(
            id = _uiState.value.proximoId,
            nombre = _uiState.value.nombre
        )
        _uiState.value = _uiState.value.copy(usuarios =_uiState.value.usuarios + nuevoUsuario, proximoId = _uiState.value.proximoId+1, nombre = "")
    }

    fun cambiarEstadoUsuario(id: Int) {
        val nuevos = mutableListOf<Usuario>()
        for (usuario in _uiState.value.usuarios) {
            if (usuario.id == id) {
                nuevos.add(usuario.copy(activo = !usuario.activo))
            } else {
                nuevos.add(usuario)
            }
        }
        _uiState.value = _uiState.value.copy(usuarios = nuevos)
    }
}
