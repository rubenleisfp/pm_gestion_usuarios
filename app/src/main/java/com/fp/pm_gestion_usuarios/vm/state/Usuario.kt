package com.fp.pm_gestion_usuarios.vm.state

data class Usuario(
    val id: Int,
    val nombre: String,
    val activo: Boolean = true
)