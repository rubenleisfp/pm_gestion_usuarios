package com.fp.pm_gestion_usuarios.vm

import androidx.lifecycle.ViewModel
import com.fp.pm_gestion_usuarios.vm.state.GestionUsuarioState
import com.fp.pm_gestion_usuarios.vm.state.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GestionUsuarioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GestionUsuarioState())
    val uiState: StateFlow<GestionUsuarioState> get() = _uiState


    fun onNombreChanged(nuevoNombre: String) {
        throw UnsupportedOperationException("A implementar por el alumno")
    }

    fun agregarUsuario() {
        throw UnsupportedOperationException("A implementar por el alumno")
    }

    fun cambiarEstadoUsuario(id: Int) {
        throw UnsupportedOperationException("A implementar por el alumno")
    }
}
