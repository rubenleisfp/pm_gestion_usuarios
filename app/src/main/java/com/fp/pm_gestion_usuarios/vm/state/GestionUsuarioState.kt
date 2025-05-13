package com.fp.pm_gestion_usuarios.vm.state

data class GestionUsuarioState (
    val usuarios: List<Usuario> = emptyList(),
    val nombre: String = "",
    val proximoId: Int = 1)



