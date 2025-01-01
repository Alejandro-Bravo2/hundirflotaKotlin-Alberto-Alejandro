fun verificarMovimiento(movimientos: mutableSet, coordenada: list<Int>): Boolean {
    var validez = true
    for (i in movimientos){
        if (movimientos[i] == coordenada){
            validez = false
        }
    }
    return validez
}