package br.edu.ifal.casajunta.model

data class Casa(
    val id: Int,
    val nome: String,
    val quantidadeMoradores: Int,
    val principal: Boolean = false
)
