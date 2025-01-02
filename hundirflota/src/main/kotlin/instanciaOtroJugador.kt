package org.alejandro.kotlin

fun crearInstanciaOtroJugador(nombreJugador: String) : FicheroUsuario {
    if (nombreJugador == "j1") {
        val jugador2 = FicheroUsuario("j2")
        return jugador2
    } else {
        val jugador2 = FicheroUsuario("j1")
        return jugador2
    }
}
