fun colocar_barco(tablero: List<String>, barco: MutableSet<String>) {
    val coordenadas: List<Int> = listOf();
    for (i in barco) {
        for (j in barco["numero"]) {
            println("Introduzca una coordenada para colocar el barco en formato => x, y")
            var coordenadaInput = readln().split(", ")
            for (k in coordenadaInput) {
                coordenadas.append(coordenadaInput[k].toInt())
            }
            coordenadas.sort()
        }
    }
}
