fun verificarFinJuego(jugador: MutableSet<String>): Boolean {
    var condicion: Boolean = true
    for (i in jugador["tablero"]) {
        if(jugador["tablero"[i]] == "B") {
            condicion = false
        }
    }
    return condicion
}