package com.fp.pm_gestion_usuarios.vm

import androidx.lifecycle.ViewModel
import com.fp.pm_gestion_usuarios.vm.state.UsuarioState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UsuarioViewModel : ViewModel() {

    private val _usuarios = MutableStateFlow<List<UsuarioState>>(emptyList())
    val usuarios: StateFlow<List<UsuarioState>> get() = _usuarios

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> get() = _nombre

    private val _proximoId = MutableStateFlow(1)

    fun onNombreChanged(nuevoNombre: String) {
        _nombre.value = nuevoNombre
    }

    fun agregarUsuario() {
        val nuevoUsuario = UsuarioState(
            id = _proximoId.value,
            nombre = _nombre.value
        )
        _usuarios.value = _usuarios.value + nuevoUsuario
        _proximoId.value = _proximoId.value + 1
        _nombre.value = ""
    }

    fun cambiarEstadoUsuario(id: Int) {
        val nuevos = mutableListOf<UsuarioState>()
        for (usuario in _usuarios.value) {
            if (usuario.id == id) {
                nuevos.add(usuario.copy(activo = !usuario.activo))
            } else {
                nuevos.add(usuario)
            }
        }
        _usuarios.value = nuevos
    }
}
